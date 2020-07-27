package com.sinigr.usersapp.modules.main.users_list.interactor

import com.sinigr.usersapp.common.interactor.subscriber.ISubscriber
import com.sinigr.usersapp.model.users.IUsersRepository
import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.network.errors.ErrorFactory
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.network_manager.ResponseResult
import com.sinigr.usersapp.network.services.IUsersNetworkService
import kotlinx.coroutines.*
import retrofit2.HttpException

class UsersListInteractor(
  private val usersRepository: IUsersRepository,
  private val usersNetworkService: IUsersNetworkService,
  private val networkManager: CoroutineNetworkManager,
  private val errorFactory: ErrorFactory
) : IUsersListInteractor {

  override var jobs: MutableSet<Job> = hashSetOf()

  override fun loadUsers(subscriber: ISubscriber<List<UserEntity>>) {
    addJob(
      CoroutineScope(Dispatchers.IO).launch {
        try {
          val result = networkManager.safeCall {
            usersNetworkService.getUsersAsync().await()
          }

          // Insert into repository in IO context
          when (result) {
            is ResponseResult.Success -> {
              usersRepository.replaceAll(result.data)
            }
          }

          withContext(Dispatchers.Main) {
            when (result) {
              is ResponseResult.Success -> {
                subscriber.onSuccess(result.data)
              }
              is ResponseResult.Fail -> {
                subscriber.onError(errorFactory.errorByResponse(result))
              }
            }

            subscriber.onFinish()
          }
        } catch (e: HttpException) {

        } catch (e: Throwable) {

        }
      }
    )
  }
}
package com.sinigr.usersapp.modules.main.edit_users.interactor

import com.sinigr.usersapp.common.interactor.subscriber.ISubscriber
import com.sinigr.usersapp.model.users.IUsersRepository
import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.network.errors.Error
import com.sinigr.usersapp.network.errors.ErrorName
import com.sinigr.usersapp.network.errors.ErrorFactory
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.network_manager.ResponseResult
import com.sinigr.usersapp.network.requests.UpdateUserRequest
import com.sinigr.usersapp.network.requests.UserRequest
import com.sinigr.usersapp.network.services.IUsersNetworkService
import kotlinx.coroutines.*
import retrofit2.HttpException

class EditUserInteractor(
  private val usersRepository: IUsersRepository,
  private val usersNetworkService: IUsersNetworkService,
  private val networkManager: CoroutineNetworkManager,
  private val errorFactory: ErrorFactory
) : IEditUserInteractor {

  override var jobs: MutableSet<Job> = hashSetOf()

  override fun getUser(id: Long, subscriber: ISubscriber<UserEntity>) {
    val user = usersRepository.findBy(id)

    if (user != null) {
      subscriber.onSuccess(user)
    } else {
      subscriber.onError(errorFactory.errorByName(ErrorName.NOT_FOUND))
    }

    subscriber.onFinish()
  }

  override fun createUser(
    firstName: String, lastName: String, email: String,
    subscriber: ISubscriber<UserEntity>
  ) {
    val requestBody = UserRequest(firstName, lastName, email)

    addJob(
      CoroutineScope(Dispatchers.IO).launch {
        try {
          val response = networkManager.safeCall {
            usersNetworkService.createUserAsync(UpdateUserRequest(requestBody)).await()
          }

          withContext(Dispatchers.Main) {
            when (response) {
              is ResponseResult.Success -> {
                usersRepository.add(response.data)
                subscriber.onSuccess(response.data)
              }
              is ResponseResult.Fail -> {
                subscriber.onError(errorFactory.errorByResponse(response))
              }
            }

            subscriber.onFinish()
          }
        } catch (e: HttpException) {
          e.printStackTrace()
        } catch (e: Throwable) {
          e.printStackTrace()
        }
      }
    )
  }

  override fun updateUser(
    id: Long, firstName: String, lastName: String, email: String,
    subscriber: ISubscriber<UserEntity>
  ) {
    val requestBody = UserRequest(firstName, lastName, email)

    addJob(
      CoroutineScope(Dispatchers.IO).launch {
        try {
          val response = networkManager.safeCall {
            usersNetworkService.updateUserAsync(id, UpdateUserRequest(requestBody)).await()
          }

          withContext(Dispatchers.Main) {
            when (response) {
              is ResponseResult.Success -> {
                usersRepository.updateBy(id, response.data)
                subscriber.onSuccess(response.data)
              }
              is ResponseResult.Fail -> {
                subscriber.onError(Error(response.code, response.message))
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
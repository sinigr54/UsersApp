package com.sinigr.usersapp.modules.main.users_list.interactor

import com.sinigr.usersapp.base.interactor.subscriber.ISubscriber
import com.sinigr.usersapp.data.users.IUsersRepository
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.network_manager.Result
import com.sinigr.usersapp.network.services.IUsersNetworkService
import kotlinx.coroutines.*
import retrofit2.HttpException

class UsersListInteractor(
    private val usersRepository: IUsersRepository,
    private val usersNetworkService: IUsersNetworkService,
    private val networkManager: CoroutineNetworkManager
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
                        is Result.Success -> {
                            usersRepository.replaceAll(result.data)
                        }
                    }

                    withContext(Dispatchers.Main) {
                        when (result) {
                            is Result.Success -> {
                                subscriber.onSuccess(result.data)
                            }
                            is Result.Error -> {
                                subscriber.onError(result.code, result.message)
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
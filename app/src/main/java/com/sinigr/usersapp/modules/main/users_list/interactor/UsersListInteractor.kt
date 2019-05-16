package com.sinigr.usersapp.modules.main.users_list.interactor

import com.sinigr.usersapp.data.users.IUsersRepository
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.network_manager.OnError
import com.sinigr.usersapp.network.network_manager.OnSuccessWithData
import com.sinigr.usersapp.network.network_manager.Result
import com.sinigr.usersapp.network.services.IUsersNetworkService
import kotlinx.coroutines.*
import retrofit2.HttpException

class UsersListInteractor(
    private val usersRepository: IUsersRepository,
    private val usersNetworkService: IUsersNetworkService,
    private val networkManager: CoroutineNetworkManager
) : IUsersListInteractor {

    override var jobs: ArrayList<Job> = arrayListOf()

    override fun loadUsers(success: OnSuccessWithData<List<UserEntity>>, error: OnError) {
        addJob(
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val result = networkManager.safeCall {
                        usersNetworkService.getUsersAsync().await()
                    }

                    withContext(Dispatchers.Main) {
                        when (result) {
                            is Result.Success -> {
                                usersRepository.replaceAll(result.data)
                                success.invoke(result.data)
                            }
                            is Result.Error -> {
                                error.invoke(result.code, result.message)
                            }
                        }
                    }
                } catch (e: HttpException) {

                } catch (e: Throwable) {

                }
            }
        )
    }

}
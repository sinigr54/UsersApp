package com.sinigr.usersapp.modules.main.edit_users.interactor

import com.sinigr.usersapp.data.users.IUsersRepository
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.network.network_manager.CoroutineNetworkManager
import com.sinigr.usersapp.network.network_manager.OnError
import com.sinigr.usersapp.network.network_manager.OnSuccessWithData
import com.sinigr.usersapp.network.network_manager.Result
import com.sinigr.usersapp.network.requests.UpdateUserRequest
import com.sinigr.usersapp.network.requests.UserRequest
import com.sinigr.usersapp.network.services.IUsersNetworkService
import kotlinx.coroutines.*
import retrofit2.HttpException

class EditUserInteractor(
    private val usersRepository: IUsersRepository,
    private val usersNetworkService: IUsersNetworkService,
    private val networkManager: CoroutineNetworkManager
) : IEditUserInteractor {

    override var jobs: ArrayList<Job> = arrayListOf()

    override fun createUser(firstName: String, lastName: String, email: String,
                            success: OnSuccessWithData<UserEntity>, error: OnError) {

        val requestBody = UserRequest(firstName, lastName, email)

        addJob(
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = networkManager.safeCall {
                        usersNetworkService.createUserAsync(UpdateUserRequest(requestBody)).await()
                    }

                    withContext(Dispatchers.Main) {
                        when (response) {
                            is Result.Success -> {
                                usersRepository.add(response.data)
                                success.invoke(response.data)
                            }
                            is Result.Error -> {
                                error.invoke(response.code, response.message)
                            }
                        }
                    }
                } catch (e: HttpException) {

                } catch (e: Throwable) {

                }
            }
        )
    }

    override fun updateUser(id: Long, firstName: String, lastName: String, email: String,
                            success: OnSuccessWithData<UserEntity>, error: OnError) {

        val requestBody = UserRequest(firstName, lastName, email)

        addJob(
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = networkManager.safeCall {
                        usersNetworkService.updateUserAsync(id, UpdateUserRequest(requestBody)).await()
                    }

                    withContext(Dispatchers.Main) {
                        when (response) {
                            is Result.Success -> {
                                usersRepository.updateBy(id, response.data)
                                success.invoke(response.data)
                            }
                            is Result.Error -> {
                                error.invoke(response.code, response.message)
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
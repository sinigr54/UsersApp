package com.sinigr.usersapp.modules.main.edit_users.interactor

import com.sinigr.usersapp.base.ICoroutineInteractor
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.network.network_manager.OnError
import com.sinigr.usersapp.network.network_manager.OnSuccessWithData

interface IEditUserInteractor : ICoroutineInteractor {
    fun getUser(id: Long, success: OnSuccessWithData<UserEntity>, error: OnError)

    fun createUser(
        firstName: String, lastName: String, email: String,
        success: OnSuccessWithData<UserEntity>, error: OnError
    )

    fun updateUser(
        id: Long, firstName: String, lastName: String, email: String,
        success: OnSuccessWithData<UserEntity>, error: OnError
    )
}
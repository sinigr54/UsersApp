package com.sinigr.usersapp.modules.main.edit_users.interactor

import com.sinigr.usersapp.base.ICoroutineInteractor
import com.sinigr.usersapp.entity.UserEntity

interface IEditUserInteractor : ICoroutineInteractor {
    fun createUser(firstName: String, lastName: String, email: String,
                   success: (UserEntity) -> Unit, error: (Int, String) -> Unit)

    fun updateUser(id: Long, firstName: String, lastName: String, email: String,
                   success: (UserEntity) -> Unit, error: (Int, String) -> Unit)
}
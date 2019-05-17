package com.sinigr.usersapp.modules.main.edit_users.view

import com.sinigr.usersapp.base.IView
import com.sinigr.usersapp.entity.UserEntity

interface IEditUserView : IView {
    fun onUserLoaded(user: UserEntity)

    fun onUserEdited(user: UserEntity)

    fun onError(message: String)
}
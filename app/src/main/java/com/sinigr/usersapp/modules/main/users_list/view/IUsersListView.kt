package com.sinigr.usersapp.modules.main.users_list.view

import com.sinigr.usersapp.base.IView
import com.sinigr.usersapp.entity.UserEntity

interface IUsersListView : IView {
    fun onUsersLoaded(users: List<UserEntity>)

    fun onError(message: String)
}
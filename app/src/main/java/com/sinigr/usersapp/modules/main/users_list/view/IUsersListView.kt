package com.sinigr.usersapp.modules.main.users_list.view

import com.sinigr.usersapp.common.IView
import com.sinigr.usersapp.domain.UserEntity

interface IUsersListView : IView {
  fun onUsersLoaded(users: List<UserEntity>)

  fun onError(message: String)
}
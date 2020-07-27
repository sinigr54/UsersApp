package com.sinigr.usersapp.modules.main.users_list.presenter

import com.sinigr.usersapp.common.IPresenter
import com.sinigr.usersapp.modules.main.users_list.view.IUsersListView

interface IUsersListPresenter : IPresenter<IUsersListView> {
  fun loadUsers()

  fun refreshUsers()
}
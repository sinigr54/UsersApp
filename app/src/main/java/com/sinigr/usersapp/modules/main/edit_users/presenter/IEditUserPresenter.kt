package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.common.IPresenter
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView

interface IEditUserPresenter : IPresenter<IEditUserView> {
  fun getUser(id: Long)

  fun createUser(firstName: String, lastName: String, email: String)

  fun updateUser(id: Long, firstName: String, lastName: String, email: String)
}
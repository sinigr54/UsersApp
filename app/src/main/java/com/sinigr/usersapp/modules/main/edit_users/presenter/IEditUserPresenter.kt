package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.base.IPresenter
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView

interface IEditUserPresenter : IPresenter<IEditUserView> {
    fun getUser(id: Long)

    fun createUser()

    fun updateUser()
}
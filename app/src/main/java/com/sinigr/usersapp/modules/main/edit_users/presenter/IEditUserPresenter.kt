package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.base.IPresenter
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView

interface IEditUserPresenter : IPresenter<IEditUserView> {
    fun createUser()

    fun updateUser()

    fun back()
}
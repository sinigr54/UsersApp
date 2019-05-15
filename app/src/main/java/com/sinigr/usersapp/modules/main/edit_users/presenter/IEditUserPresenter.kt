package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.base.IBasePresenter
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView

interface IEditUserPresenter : IBasePresenter<IEditUserView> {
    fun createUser()

    fun updateUser()

    fun back()
}
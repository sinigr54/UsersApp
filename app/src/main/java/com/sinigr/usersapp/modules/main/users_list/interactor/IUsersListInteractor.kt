package com.sinigr.usersapp.modules.main.users_list.interactor

interface IUsersListInteractor {
    fun loadUsers(success: () -> Unit, error: () -> Unit)
}
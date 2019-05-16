package com.sinigr.usersapp.modules.main.users_list.interactor

import com.sinigr.usersapp.base.ICoroutineInteractor
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.network.network_manager.OnError
import com.sinigr.usersapp.network.network_manager.OnSuccessWithData

interface IUsersListInteractor : ICoroutineInteractor {
    fun loadUsers(success: OnSuccessWithData<List<UserEntity>>, error: OnError)
}
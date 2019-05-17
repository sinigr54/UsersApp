package com.sinigr.usersapp.modules.main.users_list.interactor

import com.sinigr.usersapp.base.interactor.ICoroutineInteractor
import com.sinigr.usersapp.base.interactor.subscriber.ISubscriber
import com.sinigr.usersapp.entity.UserEntity

interface IUsersListInteractor : ICoroutineInteractor {
    fun loadUsers(subscriber: ISubscriber<List<UserEntity>>)
}
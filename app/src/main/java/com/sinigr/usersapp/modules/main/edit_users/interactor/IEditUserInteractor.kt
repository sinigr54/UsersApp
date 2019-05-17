package com.sinigr.usersapp.modules.main.edit_users.interactor

import com.sinigr.usersapp.base.interactor.ICoroutineInteractor
import com.sinigr.usersapp.base.interactor.subscriber.ISubscriber
import com.sinigr.usersapp.entity.UserEntity

interface IEditUserInteractor : ICoroutineInteractor {
    fun getUser(id: Long, subscriber: ISubscriber<UserEntity>)

    fun createUser(
        firstName: String, lastName: String, email: String,
        subscriber: ISubscriber<UserEntity>
    )

    fun updateUser(
        id: Long, firstName: String, lastName: String, email: String,
        subscriber: ISubscriber<UserEntity>
    )
}
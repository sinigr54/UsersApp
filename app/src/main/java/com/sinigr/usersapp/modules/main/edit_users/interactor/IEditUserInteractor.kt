package com.sinigr.usersapp.modules.main.edit_users.interactor

import com.sinigr.usersapp.common.interactor.ICoroutineInteractor
import com.sinigr.usersapp.common.interactor.subscriber.ISubscriber
import com.sinigr.usersapp.domain.UserEntity

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
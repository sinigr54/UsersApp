package com.sinigr.usersapp.modules.main.users_list.interactor

import com.sinigr.usersapp.common.interactor.ICoroutineInteractor
import com.sinigr.usersapp.common.interactor.subscriber.ISubscriber
import com.sinigr.usersapp.domain.UserEntity

interface IUsersListInteractor : ICoroutineInteractor {
  fun loadUsers(subscriber: ISubscriber<List<UserEntity>>)
}
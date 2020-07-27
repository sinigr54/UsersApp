package com.sinigr.usersapp.modules.main.users_list.presenter

import com.sinigr.usersapp.common.interactor.subscriber.Subscriber
import com.sinigr.usersapp.model.users.IUsersRepository
import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.view.IUsersListView
import com.sinigr.usersapp.network.errors.Error

class UsersListPresenter(
  private val interactor: IUsersListInteractor,
  private val usersRepository: IUsersRepository
) : IUsersListPresenter {

  override var view: IUsersListView? = null

  override fun loadUsers() {
    if (usersRepository.isEmpty()) {
      refreshUsers()
    } else {
      view?.onUsersLoaded(usersRepository.getUsers())
    }
  }

  override fun refreshUsers() {
    view?.showLoadingDialog()

    interactor.loadUsers(object : Subscriber<List<UserEntity>>() {
      override fun onSuccess(data: List<UserEntity>) {
        view?.onUsersLoaded(data)
      }

      override fun onError(error: Error) {
        view?.onError(error.message)
      }

      override fun onFinish() {
        view?.dismissLoadingDialog()
      }
    })
  }

  override fun detachView() {
    interactor.stopWork()

    super.detachView()
  }
}
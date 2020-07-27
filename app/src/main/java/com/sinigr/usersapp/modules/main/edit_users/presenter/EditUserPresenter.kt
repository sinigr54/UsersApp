package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.common.interactor.subscriber.Subscriber
import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView
import com.sinigr.usersapp.network.errors.Error
import com.sinigr.usersapp.network.errors.ErrorName

class EditUserPresenter(
  private val interactor: IEditUserInteractor
) : IEditUserPresenter {

  override var view: IEditUserView? = null

  override fun getUser(id: Long) {
    interactor.getUser(id, object : Subscriber<UserEntity>() {
      override fun onSuccess(data: UserEntity) {
        view?.onUserLoaded(data)
      }

      override fun onError(error: Error) {
        view?.onError(error.message)
      }
    })
  }

  override fun createUser(firstName: String, lastName: String, email: String) {
    view?.showLoadingDialog()

    interactor.createUser(firstName, lastName, email, object : Subscriber<UserEntity>() {
      override fun onSuccess(data: UserEntity) {
        view?.onUserEdited(data)
      }

      override fun onError(error: Error) {
        view?.onError(error.message)
      }

      override fun onFinish() {
        view?.dismissLoadingDialog()
      }
    })
  }

  override fun updateUser(id: Long, firstName: String, lastName: String, email: String) {
    view?.showLoadingDialog()

    interactor.updateUser(id, firstName, lastName, email, object : Subscriber<UserEntity>() {
      override fun onSuccess(data: UserEntity) {
        view?.onUserEdited(data)
      }

      override fun onError(error: Error) {
        // TODO: dependent of error code we can choose our action or message
        //  for user with help NetworkErrors class
        if (error.code == ErrorName.NOT_FOUND.code) {
          view?.onError(error.message)
          // TODO: maybe another action
        }
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
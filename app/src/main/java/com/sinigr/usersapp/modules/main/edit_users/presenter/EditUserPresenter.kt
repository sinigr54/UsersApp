package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.base.interactor.subscriber.Subscriber
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView
import com.sinigr.usersapp.network.errors.ErrorConstants

class EditUserPresenter(
    private val interactor: IEditUserInteractor
) : IEditUserPresenter {

    override var view: IEditUserView? = null

    override fun getUser(id: Long) {
        interactor.getUser(id, object : Subscriber<UserEntity>() {
            override fun onSuccess(data: UserEntity) {
                view?.onUserLoaded(data)
            }

            override fun onError(code: Int, message: String) {
                view?.onError(message)
            }
        })
    }

    override fun createUser(firstName: String, lastName: String, email: String) {
        view?.showLoadingDialog()

        interactor.createUser(firstName, lastName, email, object :
            Subscriber<UserEntity>() {
            override fun onSuccess(data: UserEntity) {
                view?.onUserEdited(data)
            }

            override fun onError(code: Int, message: String) {

            }

            override fun onFinish() {
                view?.dismissLoadingDialog()
            }
        })
    }

    override fun updateUser(id: Long, firstName: String, lastName: String, email: String) {
        view?.showLoadingDialog()

        interactor.updateUser(id, firstName, lastName, email, object :
            Subscriber<UserEntity>() {
            override fun onSuccess(data: UserEntity) {
                view?.onUserEdited(data)
            }

            override fun onError(code: Int, message: String) {
                // TODO dependent of error code we can choose our action or message
                //  for user with help NetworkErrors class
                if (code == ErrorConstants.NOT_FOUND.code) {
                    view?.onError(ErrorConstants.NOT_FOUND.message)
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
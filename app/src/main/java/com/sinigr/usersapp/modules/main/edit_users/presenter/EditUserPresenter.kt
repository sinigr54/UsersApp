package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.base.interactor.subscriber.ISubscriber
import com.sinigr.usersapp.base.interactor.subscriber.Subscriber
import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView

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

            }
        })
    }

    override fun createUser(firstName: String, lastName: String, email: String) {
        view?.showLoadingDialog()

        interactor.createUser(firstName, lastName, email, object :
            Subscriber<UserEntity>() {
            override fun onSuccess(data: UserEntity) {
                view?.onUserUpdated(data)
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
                view?.onUserUpdated(data)
            }

            override fun onError(code: Int, message: String) {

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
package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView

class EditUserPresenter(
    private val interactor: IEditUserInteractor
) : IEditUserPresenter {

    override var view: IEditUserView? = null

    override fun getUser(id: Long) {
        interactor.getUser(id, {
            view?.onUserLoaded(it)
        }, { code, message ->

        })
    }

    override fun createUser(firstName: String, lastName: String, email: String) {
        view?.showLoadingDialog()

        interactor.createUser(firstName, lastName, email, {
            view?.onUserUpdated(it)
            view?.dismissLoadingDialog()
        }, { code, message ->
            view?.dismissLoadingDialog()
        })
    }

    override fun updateUser(id: Long, firstName: String, lastName: String, email: String) {
        view?.showLoadingDialog()

        interactor.updateUser(id, firstName, lastName, email, {
            view?.onUserUpdated(it)
            view?.dismissLoadingDialog()
        }, { code, message ->
            view?.dismissLoadingDialog()
        })
    }

    override fun detachView() {
        interactor.stopWork()

        super.detachView()
    }

}
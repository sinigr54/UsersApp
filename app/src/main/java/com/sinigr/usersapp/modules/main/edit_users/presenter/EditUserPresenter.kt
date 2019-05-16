package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.router.IEditUsersRouter
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView

class EditUserPresenter(
    private val router: IEditUsersRouter,
    private val interactor: IEditUserInteractor
) : IEditUserPresenter {

    override var view: IEditUserView? = null

    override fun createUser() {
        view?.showLoadingDialog()

        interactor.createUser("A", "C", "e", {
            view?.onUserUpdated(it)
        }, { code, message ->

        })

        view?.dismissLoadingDialog()
    }

    override fun updateUser() {
        view?.showLoadingDialog()

        interactor.updateUser(1, "A", "B", "e", {
            view?.onUserUpdated(it)
        }, { code, message ->

        })

        view?.dismissLoadingDialog()
    }

    override fun back() {
        router.back()
    }

    override fun detachView() {
        interactor.stopWork()

        super.detachView()
    }
}
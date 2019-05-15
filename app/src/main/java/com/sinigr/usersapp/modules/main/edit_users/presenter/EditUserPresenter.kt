package com.sinigr.usersapp.modules.main.edit_users.presenter

import com.sinigr.usersapp.modules.main.edit_users.interactor.IEditUserInteractor
import com.sinigr.usersapp.modules.main.edit_users.router.IEditUsersRouter
import com.sinigr.usersapp.modules.main.edit_users.view.IEditUserView

class EditUserPresenter(
    private val interactor: IEditUserInteractor,
    private val router: IEditUsersRouter
) : IEditUserPresenter {

    override var view: IEditUserView? = null

    override fun createUser() {
        view?.showLoadingDialog()

        view?.dismissLoadingDialog()
    }

    override fun updateUser() {

        view?.showLoadingDialog()

        view?.dismissLoadingDialog()

    }

    override fun back() {
        router.back()
    }

    override fun detachView() {
        super.detachView()


    }
}
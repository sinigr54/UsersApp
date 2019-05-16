package com.sinigr.usersapp.modules.main.users_list.presenter

import com.sinigr.usersapp.modules.main.users_list.interactor.IUsersListInteractor
import com.sinigr.usersapp.modules.main.users_list.view.IUsersListView

class UsersListPresenter(
    private val interactor: IUsersListInteractor
) : IUsersListPresenter {

    override var view: IUsersListView? = null

    override fun loadUsers() {
        view?.showLoadingDialog()

        interactor.loadUsers({
            view?.onUsersLoaded(it)
        }, { code, message ->

        })

        view?.dismissLoadingDialog()
    }

    override fun detachView() {
        interactor.stopWork()

        super.detachView()
    }
}
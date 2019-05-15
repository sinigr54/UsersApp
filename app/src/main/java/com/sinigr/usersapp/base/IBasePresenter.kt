package com.sinigr.usersapp.base

interface IBasePresenter<View : IBaseView> {

    var view: View?

    fun attachView(view: View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

}
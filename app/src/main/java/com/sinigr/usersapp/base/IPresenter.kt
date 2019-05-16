package com.sinigr.usersapp.base

interface IPresenter<View : IView> {

    var view: View?

    fun attachView(view: View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}
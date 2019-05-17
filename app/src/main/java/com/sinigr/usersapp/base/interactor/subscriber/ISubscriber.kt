package com.sinigr.usersapp.base.interactor.subscriber

interface ISubscriber<T : Any> {

    fun onSuccess(data: T)

    fun onError(code: Int, message: String)

    fun onFinish()

}
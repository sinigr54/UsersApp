package com.sinigr.usersapp.base.interactor.subscriber

import com.sinigr.usersapp.network.errors.Error

interface ISubscriber<T : Any> {

    fun onSuccess(data: T)

    fun onError(error: Error)

    fun onFinish()

}
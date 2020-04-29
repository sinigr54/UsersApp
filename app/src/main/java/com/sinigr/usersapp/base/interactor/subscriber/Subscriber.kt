package com.sinigr.usersapp.base.interactor.subscriber

import com.sinigr.usersapp.network.errors.Error

abstract class Subscriber<T : Any> : ISubscriber<T> {

    override fun onSuccess(data: T) {

    }

    override fun onError(error: Error) {

    }

    override fun onFinish() {

    }
}
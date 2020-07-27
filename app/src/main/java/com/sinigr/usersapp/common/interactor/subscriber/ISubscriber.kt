package com.sinigr.usersapp.common.interactor.subscriber

import com.sinigr.usersapp.network.errors.Error

interface ISubscriber<T : Any> {

  fun onSuccess(data: T)

  fun onError(error: Error)

  fun onFinish()

}
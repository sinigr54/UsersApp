package com.sinigr.usersapp.common.interactor.subscriber

import com.sinigr.usersapp.network.errors.Error

abstract class Subscriber<T : Any> : ISubscriber<T> {

  override fun onSuccess(data: T) {

  }

  override fun onError(error: Error) {

  }

  override fun onFinish() {

  }
}
package com.sinigr.usersapp.network.network_manager

import retrofit2.Response

open class CoroutineNetworkManager {

  suspend fun <T : Any> safeCall(call: suspend () -> Response<T>): ResponseResult<T> {
    val response = call.invoke()

    return if (response.isSuccessful) {
      ResponseResult.Success(response.body()!!)
    } else {
      ResponseResult.Fail(response.code(), response.message())
    }
  }
}
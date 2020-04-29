package com.sinigr.usersapp.network.network_manager

import retrofit2.Response

open class CoroutineNetworkManager {

    suspend fun <T : Any> safeCall(call: suspend () -> Response<T>): Result<T> {
        val response = call.invoke()

        return if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Fail(response.code(), response.message())
        }
    }
}
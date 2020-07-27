package com.sinigr.usersapp.network.network_manager

sealed class ResponseResult<out T : Any> {
  data class Success<out T : Any>(val data: T) : ResponseResult<T>()
  data class Fail(val code: Int, val message: String) : ResponseResult<Nothing>()
}
package com.sinigr.usersapp.network.errors

import com.sinigr.usersapp.R
import com.sinigr.usersapp.application.UsersApplication

enum class ErrorConstants(val code: Int, val message: String) {
    NOT_FOUND(404, UsersApplication.instance.getString(R.string.error_not_found))
}
package com.sinigr.usersapp.network.errors

import android.content.Context
import com.sinigr.usersapp.R

class ErrorFactory(private val context: Context) {
    fun errorByName(name: ErrorName): Error {
        return when (name) {
            ErrorName.NOT_FOUND -> Error(name.code, context.getString(name.message))
        }
    }
}

data class Error(val code: Int, val message: String)

enum class ErrorName(val code: Int, val message: Int) {
    NOT_FOUND(404, R.string.error_not_found)
}
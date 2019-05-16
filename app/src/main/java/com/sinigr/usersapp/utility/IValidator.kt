package com.sinigr.usersapp.utility

interface IValidator<T> {

    fun isValid(element: T): Boolean

}
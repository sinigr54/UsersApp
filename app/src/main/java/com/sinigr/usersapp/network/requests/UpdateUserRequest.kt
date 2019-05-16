package com.sinigr.usersapp.network.requests

class UserRequest(
    private val firsName: String,
    private val lastName: String,
    private val email: String
)

class UpdateUserRequest(private val user: UserRequest)
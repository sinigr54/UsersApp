package com.sinigr.usersapp.network.requests

import com.google.gson.annotations.SerializedName

class UserRequest(
    @SerializedName("first_name")
    private val firsName: String,
    @SerializedName("last_name")
    private val lastName: String,
    @SerializedName("email")
    private val email: String
)

class UpdateUserRequest(private val user: UserRequest)
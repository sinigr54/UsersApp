package com.sinigr.usersapp.network.requests

class UserRequest(
  private val firstName: String,
  private val lastName: String,
  private val email: String
)

class UpdateUserRequest(private val user: UserRequest)
package com.sinigr.usersapp.network.services

import com.sinigr.usersapp.domain.UserEntity
import com.sinigr.usersapp.network.requests.UpdateUserRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface IUsersNetworkService {

  @GET("/users.json")
  fun getUsersAsync(): Deferred<Response<List<UserEntity>>>

  @POST("/users.json")
  fun createUserAsync(@Body request: UpdateUserRequest): Deferred<Response<UserEntity>>

  @PATCH("/users/{id}.json")
  fun updateUserAsync(
    @Path("id") id: Long,
    @Body request: UpdateUserRequest
  ): Deferred<Response<UserEntity>>

}
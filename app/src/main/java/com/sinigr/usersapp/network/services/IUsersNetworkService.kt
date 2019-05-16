package com.sinigr.usersapp.network.services

import com.sinigr.usersapp.entity.UserEntity
import com.sinigr.usersapp.network.requests.UpdateUserRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface IUsersNetworkService {

    @GET("/users.json")
    fun getUsersAsync(): Deferred<Response<List<UserEntity>>>

    @POST("/users.json")
    fun createUserAsync(request: UpdateUserRequest): Deferred<Response<UserEntity>>

    @PATCH("/users/{id}.json")
    fun updateUserAsync(@Path("id") id: Long, request: UpdateUserRequest): Deferred<Response<UserEntity>>

}
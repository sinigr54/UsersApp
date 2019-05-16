package com.sinigr.usersapp.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("created_at")
    val createdAt: String?
)
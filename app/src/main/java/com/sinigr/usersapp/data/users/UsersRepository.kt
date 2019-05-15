package com.sinigr.usersapp.data.users

import com.sinigr.usersapp.entity.UserEntity

interface UsersRepository {

    fun add(user: UserEntity)

    fun addAll(users: List<UserEntity>)

    fun remove(user: UserEntity)

    fun updateBy(id: Long, user: UserEntity)
}
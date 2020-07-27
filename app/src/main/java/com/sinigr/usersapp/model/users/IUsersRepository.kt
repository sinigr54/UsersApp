package com.sinigr.usersapp.model.users

import com.sinigr.usersapp.domain.UserEntity

// TODO implement with suspend functions for support DB async functions
interface IUsersRepository {

  fun add(user: UserEntity)

  fun addAll(users: List<UserEntity>)

  fun replaceAll(users: List<UserEntity>)

  fun remove(user: UserEntity)

  fun findBy(id: Long): UserEntity?

  fun updateBy(id: Long, user: UserEntity)

  fun getUsers(): List<UserEntity>

  fun isEmpty(): Boolean
}
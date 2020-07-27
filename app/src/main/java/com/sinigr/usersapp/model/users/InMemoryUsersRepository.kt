package com.sinigr.usersapp.model.users

import com.sinigr.usersapp.domain.UserEntity

class InMemoryUsersRepository : IUsersRepository {

  private val users: ArrayList<UserEntity> = arrayListOf()

  override fun add(user: UserEntity) {
    users.add(user)
  }

  override fun addAll(users: List<UserEntity>) {
    this.users.addAll(users)
  }

  override fun replaceAll(users: List<UserEntity>) {
    this.users.clear()
    this.users.addAll(users)
  }

  override fun remove(user: UserEntity) {
    users.remove(user)
  }

  override fun findBy(id: Long): UserEntity? {
    return users.find { it.id == id }
  }

  override fun updateBy(id: Long, user: UserEntity) {
    val index = users.indexOf(users.find { it.id == id })
    if (index != -1) {
      users[index] = user
    }
  }

  override fun getUsers(): List<UserEntity> {
    return users
  }

  override fun isEmpty(): Boolean {
    return users.isEmpty()
  }
}
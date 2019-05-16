package com.sinigr.usersapp.data.users

import com.sinigr.usersapp.entity.UserEntity

class InMemoryUsersRepository : IUsersRepository {

    private var users: ArrayList<UserEntity> = arrayListOf()

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

    override fun updateBy(id: Long, user: UserEntity) {
        // TODO
    }
}
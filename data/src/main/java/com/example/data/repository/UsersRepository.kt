package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.domain.users.Users

class UsersRepository(private val localDataSource: LocalDataSource) {

    suspend fun saveUser(users: Users) = localDataSource.saveUser(users)


    suspend fun findUserByUsername(username: String): List<Users> {
        return localDataSource.findUserByUsername(username)
    }

    suspend fun signIn(username: String, password: String): List<Users> {
        return localDataSource.signIn(username, password)
    }

}
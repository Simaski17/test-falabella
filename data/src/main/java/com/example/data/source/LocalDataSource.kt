package com.example.data.source

import com.example.domain.users.Users

interface LocalDataSource {

    suspend fun saveUser(user: Users)
    suspend fun findUserByUsername(username: String): List<Users>

}
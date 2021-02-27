package com.example.testfalabella.data.database

import com.example.data.source.LocalDataSource
import com.example.domain.users.Users
import com.example.testfalabella.data.toRoomUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: IndicatorDatabase) : LocalDataSource {

    private val indicatorDao = db.IndicatorDao()

    override suspend fun saveUser(user: Users) {
        withContext(Dispatchers.IO) { indicatorDao.insertUser(user.toRoomUser()) }
    }

}
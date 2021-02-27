package com.example.testfalabella.data.database

import com.example.data.source.LocalDataSource
import com.example.domain.indicators.Indicators
import com.example.domain.users.Users
import com.example.testfalabella.data.toDomainuser
import com.example.testfalabella.data.toRoomIndicator
import com.example.testfalabella.data.toRoomUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: IndicatorDatabase) : LocalDataSource {

    private val indicatorDao = db.IndicatorDao()

    override suspend fun saveUser(user: Users) {
        withContext(Dispatchers.IO) { indicatorDao.insertUser(user.toRoomUser()) }
    }

    override suspend fun findUserByUsername(username: String): List<Users> = withContext(Dispatchers.IO) {
        indicatorDao.findUserByUsername(username).map { it.toDomainuser() }
    }

    override suspend fun signIn(username: String, password: String): List<Users> = withContext(Dispatchers.IO) {
        indicatorDao.signIn(username, password).map { it.toDomainuser() }
    }

    override suspend fun saveIndicatorsList(indicators: List<Indicators>) {
        withContext(Dispatchers.IO) { indicatorDao.insertIndicators(indicator = indicators.map { it.toRoomIndicator() }) }
    }

}
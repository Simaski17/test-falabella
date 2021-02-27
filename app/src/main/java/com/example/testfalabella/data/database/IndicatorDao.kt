package com.example.testfalabella.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IndicatorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(users: Users)

    @Query("SELECT * FROM Users WHERE username = :username")
    fun findUserByUsername(username: String): List<Users>

}
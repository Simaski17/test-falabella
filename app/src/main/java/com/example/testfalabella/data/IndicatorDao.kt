package com.example.testfalabella.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface IndicatorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(users: Users)

}
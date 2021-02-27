package com.example.testfalabella.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Users::class, Indicators::class], version = 2)
abstract class IndicatorDatabase : RoomDatabase() {

    abstract fun IndicatorDao(): IndicatorDao

}
package com.example.testfalabella.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users (
    val name: String,
    val firstname: String,
    @PrimaryKey(autoGenerate = false)
    val username: String,
    val password: String,
    val isOnline: Boolean
)
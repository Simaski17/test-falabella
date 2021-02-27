package com.example.testfalabella.data

import com.example.domain.users.Users
import com.example.testfalabella.data.database.Users as DomainUser

fun Users.toRoomUser() : DomainUser =
    DomainUser(
        name,
        firstname,
        username,
        password
    )

fun DomainUser.toDomainuser(): Users =
    Users(
        name,
        firstname,
        username,
        password
    )
package com.example.testfalabella.data

import com.example.domain.indicators.Indicators
import com.example.domain.users.Users
import com.example.testfalabella.data.database.Users as DomainUser
import com.example.testfalabella.data.database.Indicators as DomainIndicator

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

fun Indicators.toRoomIndicator() : DomainIndicator =
    DomainIndicator(
        codigo,
        nombre,
        unidadMedida,
        fecha,
        valor
    )

fun DomainIndicator.toDomainindicator(): Indicators =
    Indicators(
        codigo,
        nombre,
        unidadMedida,
        fecha,
        valor
    )
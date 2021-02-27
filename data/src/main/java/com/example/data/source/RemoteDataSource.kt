package com.example.data.source

import com.example.domain.indicators.Indicators

interface RemoteDataSource {

    suspend fun getListIndicators(): List<Indicators>

}
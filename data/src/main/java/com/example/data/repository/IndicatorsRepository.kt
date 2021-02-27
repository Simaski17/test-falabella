package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.indicators.Indicators

class IndicatorsRepository (private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource)  {

    suspend fun getListIndicators(): List<Indicators> {
        var indicators = remoteDataSource.getListIndicators()
        localDataSource.saveIndicatorsList(indicators)
        return indicators
    }

}
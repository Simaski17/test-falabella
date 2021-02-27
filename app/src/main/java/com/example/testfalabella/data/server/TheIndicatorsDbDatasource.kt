package com.example.testfalabella.data.server

import com.example.data.source.RemoteDataSource
import com.example.domain.indicators.Indicators

class TheIndicatorsDbDatasource(private val theIndicatorsDbService: TheIndicatorsDbService) : RemoteDataSource  {

    override suspend fun getListIndicators(): List<Indicators> {
        TODO("Not yet implemented")
    }

}
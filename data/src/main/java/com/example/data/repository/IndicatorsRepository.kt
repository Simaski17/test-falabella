package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource

class IndicatorsRepository (private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource)  {
}
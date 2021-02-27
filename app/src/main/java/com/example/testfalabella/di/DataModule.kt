package com.example.testfalabella.di

import com.example.data.repository.IndicatorsRepository
import com.example.data.repository.UsersRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun usersRepositoryProvider(localDataSource: LocalDataSource) = UsersRepository(localDataSource)

    @Provides
    fun indicatorsRepositoryProvider(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) = IndicatorsRepository(localDataSource, remoteDataSource)

}
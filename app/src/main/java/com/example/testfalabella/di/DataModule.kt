package com.example.testfalabella.di

import com.example.data.repository.UsersRepository
import com.example.data.source.LocalDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun usersRepositoryProvider(localDataSource: LocalDataSource) = UsersRepository(localDataSource)

}
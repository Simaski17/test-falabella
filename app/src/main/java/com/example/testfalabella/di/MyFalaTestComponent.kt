package com.example.testfalabella.di

import android.app.Application
import com.example.testfalabella.ui.home.HomeFragmentComponent
import com.example.testfalabella.ui.home.HomeFragmentModule
import com.example.testfalabella.ui.login.LoginFragmentComponent
import com.example.testfalabella.ui.login.LoginFragmentModule
import com.example.testfalabella.ui.register.RegisterFragmentComponent
import com.example.testfalabella.ui.register.RegisterFragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyFalaTestComponent {

    fun plus(module: RegisterFragmentModule): RegisterFragmentComponent
    fun plus(module: LoginFragmentModule): LoginFragmentComponent
    fun plus(module: HomeFragmentModule): HomeFragmentComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyFalaTestComponent
    }

}
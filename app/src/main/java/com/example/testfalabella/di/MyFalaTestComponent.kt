package com.example.testfalabella.di

import android.app.Application
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

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyFalaTestComponent
    }

}
package com.example.testfalabella.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModue::class])
interface MyFalaTestComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): MyFalaTestComponent
    }

}
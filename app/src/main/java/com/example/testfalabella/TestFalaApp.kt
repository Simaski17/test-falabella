package com.example.testfalabella

import android.app.Application
import com.example.testfalabella.di.DaggerMyFalaTestComponent
import com.example.testfalabella.di.MyFalaTestComponent

class TestFalaApp : Application() {

    lateinit var component: MyFalaTestComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerMyFalaTestComponent.factory().create(this)

    }

}
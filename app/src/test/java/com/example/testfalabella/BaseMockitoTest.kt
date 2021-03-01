package com.example.testfalabella

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import kotlin.reflect.KClass
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@Suppress("UNUSED_PARAMETER")
abstract class BaseMockitoTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Suppress("EXPERIMENTAL_API_USAGE")
    protected val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun init() {
        @Suppress("EXPERIMENTAL_API_USAGE")
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun finish() {
        @Suppress("EXPERIMENTAL_API_USAGE")
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    inline fun <reified T : Any> any(): T {
        return Mockito.any(T::class.java) ?: createInstance()
    }

    inline fun <reified T : Any> createInstance(): T {
        return createInstance(T::class)
    }

    @Suppress("UNUSED_PARAMETER")
    fun <T : Any> createInstance(kClass: KClass<T>): T {
        return castNull()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> castNull(): T = null as T
}
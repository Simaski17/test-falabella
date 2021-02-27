package com.example.testfalabella.data.server

import com.example.domain.indicators.Welcome
import retrofit2.Call
import retrofit2.http.GET

interface TheIndicatorsDbService {

    @GET("api")
    fun getListIndicators(): Call<Welcome>

}
package com.example.testfalabella.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.domain.indicators.Indicators
import com.example.testfalabella.ui.common.*
import com.example.usecases.indicators.FindIndicatorByCode
import com.example.usecases.indicators.GetListIndicatorsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel (private val getListIndicatorsUseCase: GetListIndicatorsUseCase, private val findIndicatorByCode: FindIndicatorByCode): ScopedViewModel() {

    val model = MutableLiveData<Data<List<Indicators>>>()

    init {
        initScope()
    }

    fun getListIndicators() {
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    getListIndicatorsUseCase.invoke()
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: "))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }

    fun findIndicatorByCode(code: String) {
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    findIndicatorByCode.invoke(code)
                }
            }.onSuccess { response ->
                if (response.isNotEmpty()){
                    model.postValue(response)
                } else {
                    model.postException(Exception("${"Error"}: "))
                }

            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }

}
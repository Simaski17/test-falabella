package com.example.testfalabella.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.domain.users.Users
import com.example.testfalabella.ui.common.*
import com.example.usecases.users.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel (private val signInUseCase: SignInUseCase): ScopedViewModel() {

    val model = MutableLiveData<Data<List<Users>>>()

    init {
        initScope()
    }

    fun signIn(username: String, password: String) {
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    signInUseCase.invoke(username, password)
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
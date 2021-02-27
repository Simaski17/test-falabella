package com.example.testfalabella.ui.register

import androidx.lifecycle.MutableLiveData
import com.example.domain.users.Users
import com.example.testfalabella.ui.common.*
import com.example.usecases.users.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel (private val saveUserUseCase: SaveUserUseCase): ScopedViewModel()  {

    val model = MutableLiveData<Data<Boolean>>()

    fun saveUser(users: Users) {
        launch {
            model.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    saveUserUseCase.invoke(users)
                }
            }.onSuccess { response ->
                model.postValue(true)
            }.onFailure { throwable ->
                model.postException(throwable)
            }

        }
    }

}
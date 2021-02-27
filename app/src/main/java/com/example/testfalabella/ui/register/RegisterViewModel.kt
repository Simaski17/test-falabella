package com.example.testfalabella.ui.register

import androidx.lifecycle.MutableLiveData
import com.example.domain.users.Users
import com.example.testfalabella.ui.common.*
import com.example.usecases.users.FindUserByUsernameUseCase
import com.example.usecases.users.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel (private val saveUserUseCase: SaveUserUseCase, private val findUserByUsernameUseCase: FindUserByUsernameUseCase): ScopedViewModel()  {

    val model = MutableLiveData<Data<Boolean>>()
    val modelFindUser = MutableLiveData<Data<List<Users>>>()

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

    fun findUserByUsername(username: String) {
        launch {
            modelFindUser.postLoading()

            runCatching {
                withContext(Dispatchers.IO) {
                    findUserByUsernameUseCase.invoke(username)
                }
            }.onSuccess { response ->
                if (response.isEmpty()){
                    modelFindUser.postValue(response)
                } else {
                    modelFindUser.postException(Exception("${"Error"}: "))
                }

            }.onFailure { throwable ->
                modelFindUser.postException(throwable)
            }

        }
    }

}
package com.example.usecases.users

import com.example.data.repository.UsersRepository
import com.example.domain.users.Users

class SignInUseCase (private val usersRepository: UsersRepository) {
    suspend fun invoke(username: String, password: String): List<Users> = usersRepository.signIn(username, password)
}
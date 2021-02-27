package com.example.usecases.users

import com.example.data.repository.UsersRepository
import com.example.domain.users.Users

class SaveUserUseCase (private val usersRepository: UsersRepository) {
    suspend fun invoke(users: Users) = usersRepository.saveUser(users)
}
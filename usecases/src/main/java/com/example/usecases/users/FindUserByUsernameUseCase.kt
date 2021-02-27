package com.example.usecases.users

import com.example.data.repository.UsersRepository
import com.example.domain.users.Users

class FindUserByUsernameUseCase (private val usersRepository: UsersRepository) {
    suspend fun invoke(username: String): List<Users> = usersRepository.findUserByUsername(username)
}
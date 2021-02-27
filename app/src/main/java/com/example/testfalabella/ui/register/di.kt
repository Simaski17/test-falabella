package com.example.testfalabella.ui.register

import com.example.data.repository.UsersRepository
import com.example.usecases.users.FindUserByUsernameUseCase
import com.example.usecases.users.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class RegisterFragmentModule() {

    @Provides
    fun registerViewModelProvider(saveUserUseCase: SaveUserUseCase, findUserByUsernameUseCase: FindUserByUsernameUseCase): RegisterViewModel {
        return RegisterViewModel(saveUserUseCase, findUserByUsernameUseCase)
    }

    @Provides
    fun saveUser(usersRepository: UsersRepository) = SaveUserUseCase(usersRepository)

    @Provides
    fun findUserByUsername(usersRepository: UsersRepository) = FindUserByUsernameUseCase(usersRepository)

}

@Subcomponent(modules = [RegisterFragmentModule::class])
interface RegisterFragmentComponent {
    val registerViewModel: RegisterViewModel
}
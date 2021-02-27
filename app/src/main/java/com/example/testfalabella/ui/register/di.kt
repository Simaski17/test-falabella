package com.example.testfalabella.ui.register

import com.example.data.repository.UsersRepository
import com.example.usecases.users.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class RegisterFragmentModule() {

    @Provides
    fun registerViewModelProvider(saveUserUseCase: SaveUserUseCase): RegisterViewModel {
        return RegisterViewModel(saveUserUseCase)
    }

    @Provides
    fun saveUser(usersRepository: UsersRepository) = SaveUserUseCase(usersRepository)

}

@Subcomponent(modules = [RegisterFragmentModule::class])
interface RegisterFragmentComponent {
    val registerViewModel: RegisterViewModel
}
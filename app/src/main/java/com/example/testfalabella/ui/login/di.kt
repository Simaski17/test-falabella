package com.example.testfalabella.ui.login

import com.example.data.repository.UsersRepository
import com.example.usecases.users.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class LoginFragmentModule() {

    @Provides
    fun loginViewModelProvider(signInUseCase: SignInUseCase): LoginViewModel {
        return LoginViewModel(signInUseCase)
    }

    @Provides
    fun signIn(usersRepository: UsersRepository) = SignInUseCase(usersRepository)

}

@Subcomponent(modules = [LoginFragmentModule::class])
interface LoginFragmentComponent {
    val loginViewModel: LoginViewModel
}
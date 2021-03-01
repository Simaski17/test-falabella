package com.example.testfalabella.ui.register

import androidx.lifecycle.Observer
import com.example.data.repository.UsersRepository
import com.example.domain.users.Users
import com.example.testfalabella.BaseMockitoTest
import com.example.testfalabella.ui.common.Data
import com.example.testfalabella.ui.common.DataState
import com.example.usecases.users.FindUserByUsernameUseCase
import com.example.usecases.users.SaveUserUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

@RunWith(MockitoJUnitRunner::class)
@Config(manifest = Config.NONE)
@ExperimentalCoroutinesApi
class RegisterViewModelTest : BaseMockitoTest() {

    @Mock
    private lateinit var saveUserUseCase: SaveUserUseCase

    @Mock
    private lateinit var findUserByUsernameUseCase: FindUserByUsernameUseCase

    @Mock
    private lateinit var usersRepository: UsersRepository

    @Mock
    lateinit var observer: Observer<Data<Boolean>>

    @Mock
    lateinit var observerFind: Observer<Data<List<Users>>>

    @InjectMocks
    private lateinit var registerViewModel: RegisterViewModel

    @Before
    fun setup() {
        usersRepository = Mockito.mock(UsersRepository::class.java)
        saveUserUseCase = Mockito.spy(SaveUserUseCase(usersRepository))
        findUserByUsernameUseCase = Mockito.spy(FindUserByUsernameUseCase(usersRepository))
        registerViewModel = RegisterViewModel(saveUserUseCase,findUserByUsernameUseCase)
    }


    val mockedUser = Users(
        name = "Jimmy",
        firstname = "Hernandez",
        username = "qwerty",
        password = "sfdghj"
    )

    val mockedUserFailure = Users(
        name = "",
        firstname = "",
        username = "",
        password = ""
    )

    private fun getUsersList(): List<Users> {
        val list = ArrayList<Users>()
        list.add(Users(name = "Jimmy", firstname = "Hernandez", username = "qwerty", password = "sfdghj"))
        return list
    }

    private fun getUsersListEmpty(): List<Users> {
        val list = ArrayList<Users>()
        return list
    }

    @Test
    fun getSaveUserSuccessLoading() {
        runBlocking {
            whenever(saveUserUseCase.invoke(mockedUser)).thenReturn(null)
            registerViewModel.model.observeForever(observer)

            registerViewModel.saveUser(mockedUser)

            verify(observer).onChanged(Data(DataState.LOADING, null, null))
        }
    }


    @Test
    fun getFindUsersSuccessLoading() {
        runBlocking {
            whenever(findUserByUsernameUseCase.invoke("simaski")).thenReturn(getUsersListEmpty())
            registerViewModel.modelFindUser.observeForever(observerFind)

            registerViewModel.findUserByUsername("simaski")

            verify(observerFind).onChanged(Data(DataState.LOADING, null, null))

        }
    }

}


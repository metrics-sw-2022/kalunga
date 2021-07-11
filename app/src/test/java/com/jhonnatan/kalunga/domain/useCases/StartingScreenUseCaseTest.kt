package com.jhonnatan.kalunga.domain.useCases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.data.user.entities.UserRemote
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceRemote
import com.jhonnatan.kalunga.domain.models.entities.ResponseStartingUseCase
import com.jhonnatan.kalunga.domain.models.enumeration.ResponseCodeServices
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 10/07/2021 at 2:24 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 */

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class StartingScreenUseCaseTest() {

    private lateinit var userDataSourceRemote: UserDataSourceRemote
    private lateinit var userRepository: UserRepository
    private lateinit var startingScreenUseCase: StartingScreenUseCase
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private fun cloneUserServer(): UserRemote {
        return UserRemote(
            3,
            "unitTesting@kalunga.com",
            "Jhotec2013",
            0,
            3,
            "d41d8cd98f00b204e9800998ecf8427e",
            "0000",
            false,
            convertDate("0000-00-00 00:00:00"),
            convertDate("0000-00-00 00:00:00"),
            convertDate("2021-07-10 21:24:19"),
            3,
            "unitTesting@kalunga.com",
            "Jhonnatan E Zamudio P",
            0,
            "1016055000",
            "+57 311 2949556",
            "CO",
            "Bogotá"
        )
    }

    private fun convertDate(date: String): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return Date(sdf.parse(date)!!.time)
    }

    @Before
    fun setup() {
        userDataSourceRemote = UserDataSourceRemote()
        userRepository = UserRepository.getInstance(userDataSourceRemote)
        startingScreenUseCase = StartingScreenUseCase(userRepository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `Caso 01`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = startingScreenUseCase.getUserByAccountRemote("1")
            assertEquals(ResponseStartingUseCase(false,null), result)
        }
    }

    @Test
    fun `Caso 02`(): Unit = runBlocking {
        val user = cloneUserServer()
        launch(Dispatchers.Main) {
            val result = startingScreenUseCase.getUserByAccountRemote(user.account)
            assertEquals(ResponseStartingUseCase(true, listOf(user)), result)
        }
    }

    @Test
    fun `Caso 03`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = startingScreenUseCase.getUserByAccountRemote("")
            assertEquals(ResponseStartingUseCase(null, ResponseCodeServices.SERVER_ERROR.value), result)
        }
    }


}
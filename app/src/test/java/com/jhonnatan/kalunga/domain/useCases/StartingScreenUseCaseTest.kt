package com.jhonnatan.kalunga.domain.useCases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonnatan.kalunga.data.repositories.user.UserRepository
import com.jhonnatan.kalunga.data.source.remote.entities.User
import com.jhonnatan.kalunga.data.source.remote.services.UserService
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertArrayEquals
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

    private lateinit var userService: UserService
    private lateinit var userRepository: UserRepository
    private lateinit var startingScreenUseCase: StartingScreenUseCase
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private fun cloneUserServer(): User {
        return User(
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
        userService = UserService()
        userRepository = UserRepository.getInstance(userService)
        startingScreenUseCase = StartingScreenUseCase(userRepository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `Caso 01`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = startingScreenUseCase.getUserByAccountRemote("1")
            assertEquals(listOf(false), result)
        }
    }

    @Test
    fun `Caso 02`(): Unit = runBlocking {
        val user = cloneUserServer()
        launch(Dispatchers.Main) {
            val result = startingScreenUseCase.getUserByAccountRemote(user.account)
            assertEquals(listOf(true, listOf(user)), result)
        }
    }

    @Test
    fun `Caso 03`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = startingScreenUseCase.getUserByAccountRemote("")
            assertEquals(listOf(null,"Error en el servidor, por favor intente más tarde"), result)
        }
    }


}
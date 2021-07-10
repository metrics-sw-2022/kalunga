package com.jhonnatan.kalunga.domain.useCases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.data.repositories.user.UserRepository
import com.jhonnatan.kalunga.data.source.remote.services.UserService
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
            val result = startingScreenUseCase.getUserByAccountRemote("")
            assertEquals(false, result)
        }
    }
}
package com.jhonnatan.kalunga.domain.useCases

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonnatan.kalunga.data.room.KalungaDB
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceLocal
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.data.user.entities.UserRemote
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceRemote
import com.jhonnatan.kalunga.data.version.datasource.VersionDataSourceLocal
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
    private lateinit var userDataSourceLocal: UserDataSourceLocal
    private lateinit var userRepository: UserRepository
    private lateinit var startingScreenUseCase: StartingScreenUseCase
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private var context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var database: KalungaDB

    private fun cloneUserServer(): UserRemote {
        return UserRemote(
            "unitTesting@kalunga.com",
            0,
            3,
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
        database = Room.inMemoryDatabaseBuilder(
            context,
            KalungaDB::class.java
        ).allowMainThreadQueries().build()
        userDataSourceRemote = UserDataSourceRemote()
        userDataSourceLocal = UserDataSourceLocal.getInstance(database.userDAO())
        userRepository = UserRepository.getInstance(userDataSourceRemote,userDataSourceLocal)
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

    @Test
    fun `Caso 04`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = startingScreenUseCase.getUserByAccountLocal("123456")
            assertEquals(false, result)
        }
    }

}
package com.jhonnatan.kalunga.domain.useCases

import android.content.Context
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.data.source.local.dataBases.KalungaDB
import com.jhonnatan.kalunga.data.source.local.dataSources.SplashScreenDataSource
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 9:40 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 */

@RunWith(AndroidJUnit4::class)
@Suppress("NonAsciiCharacters")
@ExperimentalCoroutinesApi
class SplashScreenUseCaseTest() {


    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    companion object {
        lateinit var context: Context
        lateinit var database: KalungaDB
        lateinit var splashScreenDataSource: SplashScreenDataSource
        lateinit var splashScreenRepository: SplashScreenRepository
        lateinit var splashScreenUseCase: SplashScreenUseCase

    }

    @Before
    fun setup(){
        context = ApplicationProvider.getApplicationContext<Context>()
        database = KalungaDB.getInstance(context)
        splashScreenDataSource = SplashScreenDataSource.getInstance(database.splashScreenDAO())
        splashScreenRepository = SplashScreenRepository.getInstance(splashScreenDataSource)
        splashScreenUseCase  = SplashScreenUseCase(splashScreenRepository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
    
    @Test
    fun `Caso 1`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = splashScreenUseCase.getAppVersion()
            assertEquals("Versión " + BuildConfig.VERSION_NAME,result)
        }
    }
}
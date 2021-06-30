package com.jhonnatan.kalunga.domain.useCases

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.data.source.local.dataBases.KalungaDB
import com.jhonnatan.kalunga.data.source.local.dataSources.SplashScreenDataSource
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository
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
class SplashScreenUseCaseTest() {

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
    }
    
    @Test
    fun `Caso 1 Cuando consulta la tabla versión en base de datos y no existe ningún registro getAppVersión() debe devolver la versión de compilación`() {
        val result = splashScreenUseCase.getAppVersion()
        assertEquals("Versión " + BuildConfig.VERSION_NAME,result)
    }
}
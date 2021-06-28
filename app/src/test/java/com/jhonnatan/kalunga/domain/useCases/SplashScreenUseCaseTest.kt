package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.BuildConfig
import org.junit.Test

import org.junit.Assert.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 9:40 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 */
@Suppress("NonAsciiCharacters")
class SplashScreenUseCaseTest {

    val splashScreenUseCase  = SplashScreenUseCase()
    
    @Test
    fun `Caso 1 uando arranca la aplicación el método getAppVersión() debe devolver la versión de compilación`() {
        val result = splashScreenUseCase.getAppVersion()
        assertEquals("Versión " + BuildConfig.VERSION_NAME,result)
    }
}
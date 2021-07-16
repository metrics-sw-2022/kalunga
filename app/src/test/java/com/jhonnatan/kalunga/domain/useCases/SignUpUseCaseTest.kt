package com.jhonnatan.kalunga.domain.useCases

import androidx.room.Room
import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.data.room.KalungaDB
import com.jhonnatan.kalunga.data.version.datasource.VersionDataSourceLocal
import com.jhonnatan.kalunga.data.version.repository.VersionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 16/07/2021 at 6:28 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 */
class SignUpUseCaseTest(){
    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun setup() {
        signUpUseCase = SignUpUseCase()
    }

    @Test
    fun `Caso 01`(){
        val result = signUpUseCase.areFieldsEmpty("")
        assertEquals(true, result)
    }
}
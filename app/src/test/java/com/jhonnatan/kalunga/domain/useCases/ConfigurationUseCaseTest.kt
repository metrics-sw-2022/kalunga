package com.jhonnatan.kalunga.domain.useCases

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonnatan.kalunga.data.cities.datasource.CitiesDataSourceLocal
import com.jhonnatan.kalunga.data.cities.repository.CitiesRepository
import com.jhonnatan.kalunga.data.cities.source.CitiesJSON
import com.jhonnatan.kalunga.data.room.KalungaDB
import com.jhonnatan.kalunga.data.typeDocument.datasource.TypeDocumentDataSourceLocal
import com.jhonnatan.kalunga.data.typeDocument.repository.TypeDocumentRepository
import com.jhonnatan.kalunga.data.typeDocument.source.TypeDocumentJSON
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceLocal
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceRemote
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 23/07/2021 at 6:06 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ConfigurationUseCaseTest {
    private lateinit var userDataSourceRemote: UserDataSourceRemote
    private lateinit var userDataSourceLocal: UserDataSourceLocal
    private lateinit var citiesDataSourceLocal: CitiesDataSourceLocal
    private lateinit var typeDocumentDataSourceLocal: TypeDocumentDataSourceLocal
    private lateinit var userRepository: UserRepository
    private lateinit var citiesRepository: CitiesRepository
    private lateinit var typeDocumentRepository: TypeDocumentRepository
    private lateinit var configurationUseCase: ConfigurationUseCase
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private var context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var database: KalungaDB
    private lateinit var citiesJSON: CitiesJSON
    private lateinit var typeDocumentJSON: TypeDocumentJSON


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context,
            KalungaDB::class.java
        ).allowMainThreadQueries().build()
        userDataSourceRemote = UserDataSourceRemote()
        userDataSourceLocal = UserDataSourceLocal.getInstance(database.userDAO())
        userRepository = UserRepository.getInstance(userDataSourceRemote, userDataSourceLocal)
        citiesJSON = CitiesJSON(context)
        citiesDataSourceLocal = CitiesDataSourceLocal(citiesJSON)
        citiesRepository = CitiesRepository.getInstance(citiesDataSourceLocal)
        typeDocumentJSON = TypeDocumentJSON(context)
        typeDocumentDataSourceLocal = TypeDocumentDataSourceLocal(typeDocumentJSON)
        typeDocumentRepository = TypeDocumentRepository.getInstance(typeDocumentDataSourceLocal)
        configurationUseCase =
            ConfigurationUseCase(userRepository, citiesRepository, typeDocumentRepository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        database.clearAllTables()
        database.close()
    }

    @Test
    fun `Caso 01`() {

    }
}
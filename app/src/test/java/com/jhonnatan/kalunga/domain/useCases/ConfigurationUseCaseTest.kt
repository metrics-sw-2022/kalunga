package com.jhonnatan.kalunga.domain.useCases

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonnatan.kalunga.data.cities.datasource.CitiesDataSourceLocal
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.cities.repository.CitiesRepository
import com.jhonnatan.kalunga.data.cities.source.CitiesJSON
import com.jhonnatan.kalunga.data.room.KalungaDB
import com.jhonnatan.kalunga.data.typeDocument.datasource.TypeDocumentDataSourceLocal
import com.jhonnatan.kalunga.data.typeDocument.repository.TypeDocumentRepository
import com.jhonnatan.kalunga.data.typeDocument.source.TypeDocumentJSON
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceLocal
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceRemote
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.domain.models.entities.ResponseStartingUseCase
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.*
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
    private val faker = Faker()

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
    fun `Caso 01`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = configurationUseCase.getDataCountries()
            Assert.assertEquals(
                listOf(
                    ResponseCountries("1", "+57", "Colombia"),
                    ResponseCountries("2", "+58", "Venezuela"),
                    ResponseCountries("3", "+34", "España"),
                    ResponseCountries("4", "+39", "Italia"),
                    ResponseCountries("5", "+1", "Estados Unidos"),
                    ResponseCountries("6", "+56", "Chile"),
                    ResponseCountries("7", "+593", "Ecuador"),
                    ResponseCountries("8", "+51", "Perú")
                ), result
            )
        }
    }

    @Test
    fun `Caso 02`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = configurationUseCase.getDataCountries()
            Assert.assertEquals(
                listOf(
                    ResponseCountries("5", "+1", "Estados Unidos"),
                    ResponseCountries("3", "+34", "España"),
                    ResponseCountries("4", "+39", "Italia"),
                    ResponseCountries("8", "+51", "Perú"),
                    ResponseCountries("6", "+56", "Chile"),
                    ResponseCountries("1", "+57", "Colombia"),
                    ResponseCountries("2", "+58", "Venezuela"),
                    ResponseCountries("7", "+593", "Ecuador")
                ), result
            )
        }
    }

    @Test
    fun `Caso 03`() {
        val result = configurationUseCase.getCountryPosition("")
        Assert.assertEquals(0, result)
        val result1 = configurationUseCase.getCountryPosition(faker.name.name())
        Assert.assertEquals(0, result1)
    }
}
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
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType
import com.jhonnatan.kalunga.domain.models.utils.UtilsCountry

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

    private fun getListCountries(): List<ResponseCountries> {
        return listOf(
            ResponseCountries("1", "+57", "Colombia"),
            ResponseCountries("2", "+58", "Venezuela"),
            ResponseCountries("3", "+34", "España"),
            ResponseCountries("4", "+39", "Italia"),
            ResponseCountries("5", "+1", "Estados Unidos"),
            ResponseCountries("6", "+56", "Chile"),
            ResponseCountries("7", "+593", "Ecuador"),
            ResponseCountries("8", "+51", "Perú")
        ).sortedBy { myObject -> myObject.codPais }
    }

    private fun getListTypeDocument(): List<ResponseDocumentType> {
        return listOf(
            ResponseDocumentType("1","Cédula de Ciudadanía","CC",0),
            ResponseDocumentType("2","Cédula de Extranjería","CE",1),
            ResponseDocumentType("3","Cédula de Identidad","CI",2),
            ResponseDocumentType("4","Documento Nacional de Identidad","DNI",3),
            ResponseDocumentType("5","Documento Único de Identidad","DUI",4),
            ResponseDocumentType("6","Identificación Oficial","ID",5),
            ResponseDocumentType("7","Pasaporte","PA",6),
            ResponseDocumentType("8","Permiso de Residencia","PR",7),
            ResponseDocumentType("9","Permiso Especial de Permanencia","PEP",8),
            ResponseDocumentType("10","Registro Único de Migrantes Venezolanos","RUMV",9),
            ResponseDocumentType("11","Tarjeta de Residente Permanente","TRP",10)
        ).sortedBy { myObject -> myObject.abbreviate }
    }

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
            Assert.assertEquals(getListCountries(), result)
        }
    }

    @Test
    fun `Caso 02`() {
        val result = configurationUseCase.getCountryPosition("", getListCountries())
        Assert.assertEquals(0, result)
        val result1 = configurationUseCase.getCountryPosition(faker.name.name(), getListCountries())
        Assert.assertEquals(0, result1)
    }

    @Test
    fun `Caso 03`() {
        val result = configurationUseCase.getCountryPosition("Colombia", getListCountries())
        Assert.assertEquals(5, result)
    }

    @Test
    fun `Caso 04`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = configurationUseCase.getDataTypeDocument()
            Assert.assertEquals(getListTypeDocument(), result)
        }
    }

    @Test
    fun `Caso 05`() {
        val result = configurationUseCase.getTypeDocumentPosition("", getListTypeDocument())
        Assert.assertEquals(0, result)
        val result1 = configurationUseCase.getTypeDocumentPosition(faker.name.name(), getListTypeDocument())
        Assert.assertEquals(0, result1)
        val result2 = configurationUseCase.getTypeDocumentPosition("CC", getListTypeDocument())
        Assert.assertEquals(0, result2)
    }


    @Test
    fun `Caso 06`() {
        val result = configurationUseCase.getFormatPhone("313", UtilsCountry().getWhiteSpaceList("Colombia"))
        Assert.assertEquals("313", result)
    }


    @Test
    fun `Caso 07`() {
        val result = configurationUseCase.getFormatPhone("3133", UtilsCountry().getWhiteSpaceList("Colombia"))
        Assert.assertEquals("313 3", result)
    }


    @Test
    fun `Caso 08`() {
        val result = configurationUseCase.areFieldsEmpty("")
        Assert.assertEquals(true, result)
    }


    @Test
    fun `Caso 09`() {
        val result = configurationUseCase.areFieldsEmpty(faker.animal.name())
        Assert.assertEquals(false, result)
    }
}
package com.jhonnatan.kalunga.domain.useCases

import android.content.Context
import androidx.test.core.app.ApplicationProvider
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
import kotlinx.coroutines.newSingleThreadContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 23/07/2021 at 6:06 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class ConfigurationUseCaseTest {
    private lateinit var userDataSourceRemote: UserDataSourceRemote
    private lateinit var userDataSourceLocal: UserDataSourceLocal
    private lateinit var citiesDataSourceLocal: CitiesDataSourceLocal
    private lateinit var typeDocumentDataSourceLocal: TypeDocumentDataSourceLocal
    private lateinit var userRepository: UserRepository
    private lateinit var citiesRepository: CitiesRepository
    private lateinit var typeDocumentRepository: TypeDocumentRepository
    private lateinit var database: KalungaDB
    private lateinit var citiesJSON: CitiesJSON
    private lateinit var typeDocumentJSON: TypeDocumentJSON
    private var context = ApplicationProvider.getApplicationContext<Context>()
    private val faker = Faker()
    var configurationUseCase = ConfigurationUseCase(userRepository, citiesRepository, typeDocumentRepository)



    @Before
    fun setup() {
        citiesJSON = CitiesJSON(context)
        typeDocumentJSON= TypeDocumentJSON(context)
        userDataSourceRemote = UserDataSourceRemote()
        userDataSourceLocal = UserDataSourceLocal.getInstance(database.userDAO())
        citiesDataSourceLocal = CitiesDataSourceLocal(citiesJSON)
        typeDocumentDataSourceLocal = TypeDocumentDataSourceLocal.getInstance(typeDocumentJSON)
        userRepository = UserRepository.getInstance(userDataSourceRemote, userDataSourceLocal)
        citiesRepository = CitiesRepository.getInstance(citiesDataSourceLocal)
        typeDocumentRepository = TypeDocumentRepository.getInstance(typeDocumentDataSourceLocal)
        configurationUseCase = ConfigurationUseCase(userRepository, citiesRepository, typeDocumentRepository)
        }

    @Test
    fun `Caso 01`() {
        val result = configurationUseCase.areFieldsEmpty("")
        Assert.assertEquals(true, result)
    }
}
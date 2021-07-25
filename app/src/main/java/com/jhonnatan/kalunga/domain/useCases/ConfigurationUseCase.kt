package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.cities.repository.CitiesRepository
import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType
import com.jhonnatan.kalunga.data.typeDocument.repository.TypeDocumentRepository
import com.jhonnatan.kalunga.data.user.repository.UserRepository

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 23/07/2021 at 11:46 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class ConfigurationUseCase(
    private val userRepository: UserRepository,
    private val citiesRepository: CitiesRepository,
    private val typeDocumentRepository: TypeDocumentRepository
) {

    suspend fun getDataCountries(): List<ResponseCountries> {
        return citiesRepository.getDataCountries().sortedBy { myObject -> myObject.codPais }
    }

    suspend fun getDataTypeDocument(): List<ResponseDocumentType> {
        return typeDocumentRepository.getDataTypeDocument()
    }

    fun getCountryPosition(country: String, countriesList: List<ResponseCountries>): Int {
        var aux = 0
        for (x in countriesList.indices)
            if (countriesList[x].pais == country)
                aux = x
        return aux
    }

}
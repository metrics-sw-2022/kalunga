package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.data.cities.entities.ResponseCities
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

    fun getCountryPosition(country: String, countriesList: List<ResponseCountries>): Int {
        var aux = 0
        for (x in countriesList.indices)
            if (countriesList[x].pais == country)
                aux = x
        return aux
    }

    suspend fun getDataTypeDocument(): List<ResponseDocumentType> {
        return typeDocumentRepository.getDataTypeDocument().sortedBy { myObject -> myObject.abbreviate }
    }

    fun getTypeDocumentPosition(typeDocument: String, typeDocumentsList: List<ResponseDocumentType>): Int {
        var aux = 0
        for (x in typeDocumentsList.indices)
            if (typeDocumentsList[x].abbreviate == typeDocument)
                aux = x
        return aux
    }

    suspend fun getDataCitiesByCodeCountry(country: String): List<ResponseCities> {
        return citiesRepository.getDataCitiesByCodeCountry(country).sortedBy { myObject -> myObject.pais }
    }

    fun areFieldsEmpty(text: String): Boolean? {
        return null
    }

    fun getFormatPhone(text: String, whiteSpacesList: List<Int>) : String{
        for (id in whiteSpacesList) {
            if (id == text.length) {
                return "${text.substring(0,text.length-1)} ${text.last()}"
            }
        }
        return text
    }
}
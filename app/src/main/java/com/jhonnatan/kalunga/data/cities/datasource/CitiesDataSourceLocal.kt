package com.jhonnatan.kalunga.data.cities.datasource

import com.jhonnatan.kalunga.data.cities.entities.ResponseCities
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.cities.source.CitiesJSONInterface

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.cities.datasource
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class CitiesDataSourceLocal private constructor(private val citiesJSONInterface: CitiesJSONInterface) {

    companion object {
        private var INSTANCE: CitiesDataSourceLocal? = null
        fun getInstance(citiesJSONInterface: CitiesJSONInterface): CitiesDataSourceLocal =
            INSTANCE ?: CitiesDataSourceLocal(citiesJSONInterface)
    }

    suspend fun getDataCountries(): List<ResponseCountries> {
        return citiesJSONInterface.getDataCountries()
    }

    suspend fun getDataCitiesByCodeCountry(country: String): List<ResponseCities> {
        return citiesJSONInterface.getDataCitiesByCodeCountry(country)
    }
}
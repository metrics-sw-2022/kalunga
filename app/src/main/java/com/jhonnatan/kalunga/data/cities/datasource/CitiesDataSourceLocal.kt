package com.jhonnatan.kalunga.data.cities.datasource

import com.jhonnatan.kalunga.data.cities.entities.ResponseCities
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.cities.datasource
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class CitiesDataSourceLocal {
    fun getDataCountries(): List<ResponseCountries> {
        return emptyList()
    }

    fun getDataCitiesByCodeCountry(country: String): List<ResponseCities> {
        return emptyList()
    }
}
package com.jhonnatan.kalunga.data.cities.source

import com.jhonnatan.kalunga.data.cities.entities.ResponseCities
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.cities.source
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:11 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
interface CitiesJSONInterface {

    suspend fun getDataCountries() : Any

    suspend fun getDataCitiesByCodeCountry(country: String) : List<ResponseCities>

}
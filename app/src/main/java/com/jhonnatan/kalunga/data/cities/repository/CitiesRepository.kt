package com.jhonnatan.kalunga.data.cities.repository

import com.jhonnatan.kalunga.data.cities.datasource.CitiesDataSourceLocal
import com.jhonnatan.kalunga.data.cities.entities.ResponseCities
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceLocal
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceRemote
import com.jhonnatan.kalunga.data.user.repository.UserRepository

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.cities.repository
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:09 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class CitiesRepository(private val citiesDataSourceLocal: CitiesDataSourceLocal) : CitiesRepositoryInterface {
    companion object{
        @Volatile
        private var instance: CitiesRepository? = null
        fun getInstance(
            citiesDataSourceLocal: CitiesDataSourceLocal
        ): CitiesRepository =
            instance ?: synchronized(this) {
                instance ?: CitiesRepository(citiesDataSourceLocal)
            }
    }

    override suspend fun getDataCountries():List<ResponseCountries>{
        return citiesDataSourceLocal.getDataCountries()
    }

    override suspend fun getDataCitiesByCodeCountry(country: String):List<ResponseCities>{
        return citiesDataSourceLocal.getDataCitiesByCodeCountry(country)
    }
}
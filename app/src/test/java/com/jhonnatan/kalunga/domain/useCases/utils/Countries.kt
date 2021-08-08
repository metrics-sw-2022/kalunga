package com.jhonnatan.kalunga.domain.useCases.utils

import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases.utils
 * Created by Jhonnatan E. Zamudio P. on 7/08/2021 at 9:35 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class Countries {

    fun getList(): List<ResponseCountries> {
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
}
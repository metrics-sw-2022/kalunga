package com.jhonnatan.kalunga.domain.models.enumeration

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models.enumeration
 * Created by Laura S. Sarmiento M. on 26/07/2021 at 2:31 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
enum class EnumerationWhiteSpaces(val code: List<Int>) {
    COLOMBIA(mutableListOf(3)),
    VENEZUELA(mutableListOf(3)),
    ITALIA(mutableListOf(3,7)),
    ESPANA(mutableListOf(3,6,9)),
    ESTADOS_UNIDOS(mutableListOf(3,7)),
    CHILE(mutableListOf(3,7)),
    ECUADOR(mutableListOf(1)),
    PERU(mutableListOf(3,7))
}
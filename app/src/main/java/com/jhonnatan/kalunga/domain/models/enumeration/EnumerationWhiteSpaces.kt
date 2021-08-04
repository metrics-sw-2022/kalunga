package com.jhonnatan.kalunga.domain.models.enumeration

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models.enumeration
 * Created by Laura S. Sarmiento M. on 26/07/2021 at 2:31 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
enum class EnumerationWhiteSpaces(val code: List<Int>) {
    COLOMBIA(mutableListOf(4)),
    VENEZUELA(mutableListOf(4)),
    ITALIA(mutableListOf(4,8)),
    ESPANA(mutableListOf(4,7,10)),
    ESTADOS_UNIDOS(mutableListOf(4,8)),
    CHILE(mutableListOf(4,8)),
    ECUADOR(mutableListOf(2)),
    PERU(mutableListOf(4,8))
}
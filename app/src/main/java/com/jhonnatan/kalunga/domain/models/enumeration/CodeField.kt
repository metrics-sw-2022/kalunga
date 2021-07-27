package com.jhonnatan.kalunga.domain.models.enumeration

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models.enumeration
 * Created by Laura S. Sarmiento M. on 16/07/2021 at 6:06 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
enum class CodeField (val code: Int) {
    EMAIL_FIELD(0x1),
    NAME_FIELD(0x2),
    PASSWORD_FIELD(0x3),
    PASSWORD_CONFIRM_FIELD(0x4),
    IDENTIFICATION_FIELD(0x5),
    PHONE_FIELD(0x6),
    CITY_FIELD(0x7)
}
package com.jhonnatan.kalunga.domain.models.enumeration

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models
 * Created by Jhonnatan E. Zamudio P. on 1/07/2021 at 9:31 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

enum class CodeTypeUser(val code: Int) {
    STANDART(0x0),
    PARTNER(0X1),
    ADMINISTRATOR(0X2),
    SUPERUSER(0X3)
}
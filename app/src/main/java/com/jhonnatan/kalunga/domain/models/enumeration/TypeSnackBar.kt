package com.jhonnatan.kalunga.domain.models.enumeration

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models
 * Created by Jhonnatan E. Zamudio P. on 3/07/2021 at 12:31 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

enum class TypeSnackBar (val code: Int) {
    CLOSE_APP(0x1),
    ERROR(0X2),
    INFO(0x3),
    WARNING(0X4)
}
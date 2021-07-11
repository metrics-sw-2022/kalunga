package com.jhonnatan.kalunga.domain.models.enumeration

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models
 * Created by Jhonnatan E. Zamudio P. on 1/07/2021 at 9:31 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

enum class CodeStatusUser(val code: Int) {
    UNVALIDATED_USER(0x0),
    BLOCKED_USER(0X1),
    CHANGING_PASSWORD_USER(0X2),
    ENABLED_USER(0X3)
}
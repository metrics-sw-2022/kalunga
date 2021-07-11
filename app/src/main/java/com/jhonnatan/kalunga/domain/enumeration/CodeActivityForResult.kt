package com.jhonnatan.kalunga.domain.enumeration

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models
 * Created by Jhonnatan E. Zamudio P. on 1/07/2021 at 9:31 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

enum class CodeActivityForResult(val code: Int) {
    IMMEDIATE_APP_UPDATE_REQ_CODE(0x1),
    LOGIN_GOOGLE(0X2)
}
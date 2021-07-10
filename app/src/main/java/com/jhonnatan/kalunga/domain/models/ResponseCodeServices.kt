package com.jhonnatan.kalunga.domain.models

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models
 * Created by Jhonnatan E. Zamudio P. on 1/07/2021 at 9:31 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

enum class ResponseCodeServices(val value: String) {
    USER_DOES_NOT_EXIST_DB("No existe el usuario en la base de datos"),
    SERVER_ERROR("Error en el servidor, por favor intente más tarde")
}
package com.jhonnatan.kalunga.domain.models.entities

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models.entities
 * Created by Jhonnatan E. Zamudio P. on 13/07/2021 at 6:29 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

data class UserAccountData(
    var id: String,
    var name: String,
    var email: String,
    var password: String
)
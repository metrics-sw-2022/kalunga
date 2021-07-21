package com.jhonnatan.kalunga.domain.models.enumeration

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models.enumeration
 * Created by Laura S. Sarmiento M. on 20/07/2021 at 1:48 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
enum class CodePatterns(val value: String) {
    EMAIL_VALIDATION("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
}
package com.jhonnatan.kalunga.domain.useCases

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 16/07/2021 at 6:21 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class SignUpUseCase {

    private var emailValidation =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    fun areFieldsEmpty(text: String) : Boolean {
        return text.isEmpty()
    }

    fun isValidEmail(text: String) : Boolean {
        return text.matches(emailValidation.toRegex())
    }

}
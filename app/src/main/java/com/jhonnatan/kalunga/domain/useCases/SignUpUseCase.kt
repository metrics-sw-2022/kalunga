package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.domain.models.enumeration.CodePatterns
import com.jhonnatan.kalunga.domain.models.enumeration.ResponseErrorField

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 16/07/2021 at 6:21 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class SignUpUseCase {

    fun areFieldsEmpty(text: String): Boolean {
        return text.isEmpty()
    }

    fun isValidEmail(text: String): Boolean {
        return text.matches(CodePatterns.EMAIL_VALIDATION.value.toRegex())
    }

    fun isValidLong(text: String, min: Int): Boolean {
        return text.length > min
    }

    fun arePasswordsEqual(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun changeEnableButton(email: Int, name: Int, password: Int, confirmPassword: Int,
    ): Boolean? {
        if (email == 1 && name == 1 && password == 1 && confirmPassword == 1) {
            return true
        } else {
            return false
        }
    }

    fun isNumberPair(number: Int) :Boolean {
        return number % 2 == 0
    }

}
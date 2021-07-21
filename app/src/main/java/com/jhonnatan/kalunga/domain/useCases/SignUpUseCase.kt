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

    fun changeEnableButton(
        email: String,
        name: String,
        password: String,
        confirmPassword: String,
        emailErrorText: String,
        nameErrorText: String,
        passwordErrorText: String,
        confirmPasswordErrorText: String
    ): Boolean {
        return emailErrorText == ResponseErrorField.DEFAULT.value && nameErrorText == ResponseErrorField.DEFAULT.value && passwordErrorText == ResponseErrorField.DEFAULT.value && confirmPasswordErrorText == ResponseErrorField.DEFAULT.value && email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }

    fun isNumberPair(number: Int) :Boolean {
        return number % 2 == 0
    }

}
package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.domain.models.enumeration.ResponseErrorField

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

    fun isValidLong(text: String, min: Int) : Boolean {
        return text.length > min
    }

    fun arePasswordsEqual(password: String, confirmPassword: String) : Boolean {
        return password.equals(confirmPassword)
    }

    fun changeEnableButton(email: String, name: String, password:String, confirmPassword: String) : Boolean {
        return email.equals(ResponseErrorField.DEFAULT.value) && name.equals(ResponseErrorField.DEFAULT.value) && password.equals(ResponseErrorField.DEFAULT.value) && confirmPassword.equals(ResponseErrorField.DEFAULT.value)
    }

}
package com.jhonnatan.kalunga.domain.useCases

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 16/07/2021 at 6:21 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class SignUpUseCase {

    fun areFieldsEmpty(text: String) : Boolean? {
        if (text.isEmpty()){
            return true
        } else {
            return false
        }
    }

}
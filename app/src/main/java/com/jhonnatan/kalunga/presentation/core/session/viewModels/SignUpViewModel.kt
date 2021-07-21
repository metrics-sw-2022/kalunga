package com.jhonnatan.kalunga.presentation.core.session.viewModels

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.domain.models.entities.UserAccountData
import com.jhonnatan.kalunga.domain.models.enumeration.CodeField
import com.jhonnatan.kalunga.domain.models.enumeration.CodeLong
import com.jhonnatan.kalunga.domain.models.enumeration.ResponseErrorField
import com.jhonnatan.kalunga.domain.useCases.SignUpUseCase
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.session.viewModels
 * Created by Laura S. Sarmiento M. on 16/07/2021 at 5:32 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class SignUpViewModel : ViewModel() {

    val errorEmail = MutableLiveData<String>()
    val errorName = MutableLiveData<String>()
    val errorPassword = MutableLiveData<String>()
    val errorPasswordConfirm = MutableLiveData<String>()
    val buttonContinueDrawable = MutableLiveData<Int>()
    val buttonContinueEnable = MutableLiveData<Boolean>()
    val signUpUseCase = SignUpUseCase()
    val navigateToConfiguration = MutableLiveData<Boolean>()
    val navigateToLogIn = MutableLiveData<Boolean>()
    val userAccount = MutableLiveData<UserAccountData>()

    init {
        navigateToConfiguration.value = false
        navigateToLogIn.value = false
        errorEmail.value = ResponseErrorField.DEFAULT.value
        errorName.value = ResponseErrorField.DEFAULT.value
        errorPassword.value = ResponseErrorField.DEFAULT.value
        errorPasswordConfirm.value = ResponseErrorField.DEFAULT.value
    }

    fun areFieldsEmpty(text: Editable?, field: Int) {

        if (signUpUseCase.areFieldsEmpty(text.toString())) {
            setErrorText(field, ResponseErrorField.ERROR_EMPTY.value)
        } else {
            setErrorText(field, ResponseErrorField.DEFAULT.value)
            when (field) {
                CodeField.EMAIL_FIELD.code -> isValidEmail(text)
                CodeField.NAME_FIELD.code -> isValidLong(text,CodeField.NAME_FIELD.code,CodeLong.NAME_FIELD.code)
                CodeField.PASSWORD_FIELD.code -> isValidLong(text,CodeField.PASSWORD_FIELD.code,CodeLong.PASSWORD_FIELD.code)
                CodeField.PASSWORD_CONFIRM_FIELD.code -> arePasswordsEqual(text,"")
            }
        }
    }

    private fun setErrorText(field: Int, value: String) {
        when (field) {
            CodeField.EMAIL_FIELD.code -> errorEmail.value = value
            CodeField.NAME_FIELD.code -> errorName.value = value
            CodeField.PASSWORD_FIELD.code -> errorPassword.value = value
            CodeField.PASSWORD_CONFIRM_FIELD.code -> errorPasswordConfirm.value = value
        }
    }

    fun isValidEmail(text: Editable?) {
        if (signUpUseCase.isValidEmail(text.toString())) {
            setErrorText(CodeField.EMAIL_FIELD.code, ResponseErrorField.DEFAULT.value)
        } else {
            setErrorText(CodeField.EMAIL_FIELD.code, ResponseErrorField.ERROR_INVALID_MAIL.value)
        }
    }

    fun isValidLong(text: Editable?, code: Int, minValue: Int) {
        if (signUpUseCase.isValidLong(text.toString(), minValue)) {
            setErrorText(code, ResponseErrorField.DEFAULT.value)
        } else {
            setErrorText(
                code,
                ResponseErrorField.ERROR_LONG_CHARACTERS.value + minValue + ResponseErrorField.ERROR_CHARACTERS.value
            )
        }
    }

    fun arePasswordsEqual(confirmPassword: Editable?, password: String) {
        if (signUpUseCase.arePasswordsEqual(confirmPassword.toString(), password)) {
            setErrorText(CodeField.PASSWORD_CONFIRM_FIELD.code, ResponseErrorField.DEFAULT.value)
        } else {
            setErrorText(
                CodeField.PASSWORD_CONFIRM_FIELD.code,
                ResponseErrorField.ERROR_PASSWORD_DOESNT_MATCH.value
            )
        }
    }

    fun changeEnableButton(email: String, name: String, password: String, confirmPassword: String) {
        if (signUpUseCase.changeEnableButton(
                email,
                name,
                password,
                confirmPassword,
                errorEmail.value.toString(),
                errorName.value.toString(),
                errorPassword.value.toString(),
                errorPasswordConfirm.value.toString()
            )
        ) {
            buttonContinueDrawable.value = R.drawable.boton_oscuro
            buttonContinueEnable.value = true
        } else {
            buttonContinueDrawable.value = R.drawable.boton_oscuro_disabled
            buttonContinueEnable.value = false
        }
    }

    fun navigateToConfiguration() {
        navigateToConfiguration.value = true
    }

    fun navigateToLogIn() {
        navigateToLogIn.value = true
    }
}


@DelicateCoroutinesApi
@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: SignUpViewModelFactory? = null
        fun getInstance(): SignUpViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: SignUpViewModelFactory()
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel() as T
    }
}
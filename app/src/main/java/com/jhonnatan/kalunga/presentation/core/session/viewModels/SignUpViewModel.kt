package com.jhonnatan.kalunga.presentation.core.session.viewModels

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.domain.models.enumeration.CodeField
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
    val signUpUseCase = SignUpUseCase()

    init {
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
package com.jhonnatan.kalunga.presentation.core.home.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.data.repositories.user.UserRepository
import com.jhonnatan.kalunga.domain.common.utils.UtilsNetwork
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import com.jhonnatan.kalunga.domain.useCases.StartingScreenUseCase
import kotlinx.coroutines.DelicateCoroutinesApi

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.home.viewModels
 * Created by Jhonnatan E. Zamudio P. on 4/07/2021 at 5:35 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class StartingScreenViewModel(userRepository: UserRepository) : ViewModel() {

    val navigateToSignUp = MutableLiveData<Boolean>()
    val loginGoogle = MutableLiveData<Boolean>()
    val isConected = MutableLiveData<Boolean>()
    val snackBarTextError = MutableLiveData<String>()
    val startingScreenUseCase = StartingScreenUseCase(userRepository)

    init {
        navigateToSignUp.value = false
        loginGoogle.value = false
    }

    fun navigateToSignUp() {
        navigateToSignUp.value = true
    }

    fun loginGoogle() {
        loginGoogle.value = true
    }

    fun checkOnline(context: Context) {
        isConected.postValue(UtilsNetwork().isOnline(context))
    }

    fun serverUserExist() {

    }

}

@DelicateCoroutinesApi
@Suppress("UNCHECKED_CAST")
class StartingScreenViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: StartingScreenViewModelFactory? = null
        fun getInstance(context: Context): StartingScreenViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: StartingScreenViewModelFactory(
                    Injection.providerStartingScreenRepository()
                )
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StartingScreenViewModel(userRepository) as T
    }
}
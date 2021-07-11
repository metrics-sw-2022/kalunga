package com.jhonnatan.kalunga.presentation.core.home.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.jhonnatan.kalunga.data.repositories.user.UserRepository
import com.jhonnatan.kalunga.domain.common.utils.UtilsNetwork
import com.jhonnatan.kalunga.domain.enumeration.ResponseCodeServices
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import com.jhonnatan.kalunga.domain.useCases.StartingScreenUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

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
    val loadingDialog = MutableLiveData<Boolean>()
    val navigateToConfiguration = MutableLiveData<Boolean>()
    val userAccount = MutableLiveData<GoogleSignInAccount>()

    init {
        navigateToSignUp.value = false
        loginGoogle.value = false
        navigateToConfiguration.value = false
    }

    fun navigateToSignUp() {
        navigateToSignUp.value = true
    }

    fun loginGoogle() {
        loadingDialog.value = true
        loginGoogle.value = true
    }

    fun checkOnline(context: Context) {
        isConected.postValue(UtilsNetwork().isOnline(context))
    }

    fun serverUserExist(acct: GoogleSignInAccount) {
        userAccount.value = acct
        viewModelScope.launch {
            val result = startingScreenUseCase.getUserByAccountRemote(acct.id)
            when(result.status){
                false -> navigateToConfiguration()
                true -> print("true")
                null -> {
                    loadingDialog.value = false
                    snackBarTextError.value = ResponseCodeServices.SERVER_ERROR.value
                }

            }
        }
    }
    fun navigateToConfiguration() {
        loadingDialog.value = false
        navigateToConfiguration.value = true
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
package com.jhonnatan.kalunga.presentation.core.home.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.jhonnatan.kalunga.data.user.entities.User
import com.jhonnatan.kalunga.data.user.entities.UserRemote
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.domain.models.utils.UtilsNetwork
import com.jhonnatan.kalunga.domain.models.enumeration.ResponseCodeServices
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import com.jhonnatan.kalunga.domain.models.enumeration.TypeLogin
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
    val navigateToDashboard = MutableLiveData<Boolean>()
    val typeLogin = MutableLiveData<String>()
    val loginFacebook = MutableLiveData<Boolean>()

    init {
        navigateToSignUp.value = false
        loginGoogle.value = false
        navigateToConfiguration.value = false
        navigateToDashboard.value = false
        typeLogin.value = ""
        loginFacebook.value = false
    }

    fun navigateToSignUp() {
        navigateToSignUp.value = true
    }

    fun loginGoogle() {
        typeLogin.value = TypeLogin.GOOGLE.value
        loadingDialog.value = true
        loginGoogle.value = true
    }

    fun loginFacebook() {
        typeLogin.value = TypeLogin.FACEBOOK.value
        loadingDialog.value = true
        loginFacebook.value = true
    }

    fun checkOnline(context: Context) {
        isConected.postValue(UtilsNetwork().isOnline(context))
    }

    @Suppress("UNCHECKED_CAST")
    fun serverUserExist(acct: GoogleSignInAccount) {
        userAccount.value = acct
        viewModelScope.launch {
            val result = startingScreenUseCase.getUserByAccountRemote(acct.id!!)
            when(result.status){
                false -> navigateToConfiguration()
                true -> userExistDatabase(result.message as List<UserRemote>)
                null -> {
                    loadingDialog.value = false
                    snackBarTextError.value = ResponseCodeServices.SERVER_ERROR.value
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun userExistDatabase(user: List<UserRemote>) {
        viewModelScope.launch {
            val result = startingScreenUseCase.getUserByAccountLocal(user[0].account)
            if (result.status!!) {
                startingScreenUseCase.updateUserLocal(result.message as List<User>)
                navigateToDashboard()
            } else {
                startingScreenUseCase.createUserLocal(user[0])
                navigateToDashboard()
            }
        }
    }

    private fun navigateToDashboard() {
        loadingDialog.value = false
        navigateToDashboard.value = true
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
                    Injection.providerStartingScreenRepository(context)
                )
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StartingScreenViewModel(userRepository) as T
    }
}
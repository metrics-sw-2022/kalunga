package com.jhonnatan.kalunga.presentation.core.home.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jhonnatan.kalunga.data.user.entities.User
import com.jhonnatan.kalunga.data.user.entities.UserRemote
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.domain.models.utils.UtilsNetwork
import com.jhonnatan.kalunga.domain.models.enumeration.ResponseCodeServices
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import com.jhonnatan.kalunga.domain.models.entities.UserAccountData
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

    val snackBarTextWarning = MutableLiveData<String>()
    val snackBarTextError = MutableLiveData<String>()
    private val startingScreenUseCase = StartingScreenUseCase(userRepository)
    val loadingDialog = MutableLiveData<Boolean>()
    val navigateToConfiguration = MutableLiveData<Boolean>()
    val userAccount = MutableLiveData<UserAccountData>()
    val navigateToDashboard = MutableLiveData<Boolean>()

    fun checkOnline(context: Context): Boolean {
        return UtilsNetwork().isOnline(context)
    }

    @Suppress("UNCHECKED_CAST")
    fun serverUserExist() {
        viewModelScope.launch {
            val result = startingScreenUseCase.getUserByAccountRemote(userAccount.value!!.id)
            when (result.status) {
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
            if (result.status!!)
                startingScreenUseCase.updateUserLocal(result.message as List<User>)
            else
                startingScreenUseCase.createUserLocal(user[0])
            startRemoteSession(user[0].account)
        }
    }

    private fun startRemoteSession(account: String) {
        viewModelScope.launch {
            startingScreenUseCase.startRemoteSession(account)
            navigateToDashboard()
        }
    }

    private fun navigateToDashboard() {
        loadingDialog.value = false
        navigateToDashboard.value = true
    }

    private fun navigateToConfiguration() {
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
                    Injection.providerUserRepository(context)
                )
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StartingScreenViewModel(userRepository) as T
    }
}
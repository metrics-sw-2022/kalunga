package com.jhonnatan.kalunga.presentation.core.home.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.data.repositories.VersionRepository
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import kotlinx.coroutines.DelicateCoroutinesApi

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.home.viewModels
 * Created by Jhonnatan E. Zamudio P. on 4/07/2021 at 5:35 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class StartingScreenViewModel: ViewModel() {

    val navigateToSignUp = MutableLiveData<Boolean>()
    val loginGoogle = MutableLiveData<Boolean>()

    init {
        navigateToSignUp.value = false
        loginGoogle.value = false
    }

    fun navigateToSignUp (){
        navigateToSignUp.value = true
    }

    fun loginGoogle (){
        loginGoogle.value = true
    }

}

@DelicateCoroutinesApi
@Suppress("UNCHECKED_CAST")
class StartingScreenViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: StartingScreenViewModelFactory? = null
        fun getInstance(context: Context): StartingScreenViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: StartingScreenViewModelFactory()
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StartingScreenViewModel() as T
    }
}
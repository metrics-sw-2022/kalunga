package com.jhonnatan.kalunga.presentation.core.home.viewModels


import android.content.Context
import androidx.lifecycle.*
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository
import com.jhonnatan.kalunga.domain.common.utils.UtilsNetwork
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import com.jhonnatan.kalunga.domain.useCases.SplashScreenUseCase
import com.jhonnatan.kalunga.presentation.core.home.views.SplashScreenActivity
import kotlinx.coroutines.*
import pub.devrel.easypermissions.EasyPermissions

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.Core.Home.SplashScreen.Presentation.ViewModel
 * Created by Jhonnatan E. Zamudio P. on 27/06/2021 at 8:59 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

@DelicateCoroutinesApi
class SplashScreenViewModel(repository: SplashScreenRepository) : ViewModel() {

    val version = MutableLiveData<String>()
    val splashScreenUseCase = SplashScreenUseCase(repository)
    val loading = MutableLiveData<Boolean>()
    val validatePermissions = MutableLiveData<Boolean>()
    val snackBarTextCloseApp = MutableLiveData<String>()
    val isConected = MutableLiveData<Boolean>()
    val hasPermission = MutableLiveData<Boolean>()
    val typePermission = MutableLiveData<String>()
    val codPermission = MutableLiveData<Int>()
    val messagePermission = MutableLiveData<String>()
    val stateCode = MutableLiveData<Boolean>()

    init {
        GlobalScope.launch {
            loading.postValue(true)
            withContext(Dispatchers.IO) {
                getAppVersion()
                validatePermissions.postValue(true)
            }
        }
    }

    fun setVersion(v: String) {
        version.value = v
    }

    fun getAppVersion() {
        viewModelScope.launch {
            setVersion(splashScreenUseCase.getAppVersion())
        }
    }

    fun checkOnline(context: Context) {
        isConected.postValue(UtilsNetwork().isOnline(context))
    }

    fun hasPermission(context: Context, permission: String){
        hasPermission.postValue(EasyPermissions.hasPermissions(context, permission))
        typePermission.postValue(permission)
        codPermission.postValue(splashScreenUseCase.getCodePermission(permission))
        messagePermission.postValue("splashScreenUseCase.getMessagePermission(permission)")
    }
}


@DelicateCoroutinesApi
@Suppress("UNCHECKED_CAST")
class SplashScreenViewModelFactory(private val mSplashScreenRepository: SplashScreenRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: SplashScreenViewModelFactory? = null
        fun getInstance(context: Context): SplashScreenViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: SplashScreenViewModelFactory(Injection.providerSplashScreenRepository(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashScreenViewModel(mSplashScreenRepository) as T
    }
}


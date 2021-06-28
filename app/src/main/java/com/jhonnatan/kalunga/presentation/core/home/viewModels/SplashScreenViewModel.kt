package com.jhonnatan.kalunga.presentation.core.home.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonnatan.kalunga.domain.useCases.SplashScreenUseCase

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.Core.Home.SplashScreen.Presentation.ViewModel
 * Created by Jhonnatan E. Zamudio P. on 27/06/2021 at 8:59 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenViewModel: ViewModel() {

    val version = MutableLiveData<String>()
    val splashScreenUseCase  = SplashScreenUseCase()

    init {
        getAppVersion()
    }

    fun setVersion(v:String){
        version.value = v
    }

    fun getAppVersion(){
        setVersion(splashScreenUseCase.getAppVersion())
    }


}
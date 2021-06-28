package com.jhonnatan.kalunga.core.home.splashScreen.presentation.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.content.res.loader.ResourcesProvider
import android.provider.Settings.Global.getString
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonnatan.kalunga.R
import kotlin.coroutines.coroutineContext

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.Core.Home.SplashScreen.Presentation.ViewModel
 * Created by Jhonnatan E. Zamudio P. on 27/06/2021 at 8:59 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenViewModel: ViewModel() {

    val version = MutableLiveData<String>()

    init {
        setVersion("Versión 1.5.0")
    }

    fun setVersion(v:String){
        version.value = v
    }


}
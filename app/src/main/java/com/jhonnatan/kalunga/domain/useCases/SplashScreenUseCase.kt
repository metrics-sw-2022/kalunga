package com.jhonnatan.kalunga.domain.useCases

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jhonnatan.kalunga.data.source.local.entities.Version
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 9:26 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenUseCase (private val repository: SplashScreenRepository){

    fun getAppVersion():String {
        var versionName = "Versión 1.5.0"
        val version  = repository.queryLast()
        version.map { x ->
            versionName = x.versionName
        }
        return  versionName
    }

}
package com.jhonnatan.kalunga.domain.useCases

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
        val version = repository.queryLast()
        return version.toString()
    }

}
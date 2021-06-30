package com.jhonnatan.kalunga.domain.useCases

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.data.source.local.entities.Version
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository
import java.util.*
import java.util.jar.Pack200.Packer.TRUE

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 9:26 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenUseCase (private val repository: SplashScreenRepository){

    suspend fun getAppVersion():String {
        val versionName: String
        if (repository.queryLast().size == 1){
            versionName= repository.queryLast()[0].versionName
        } else {
            versionName = BuildConfig.VERSION_NAME
            repository.insert(
                Version(
                    0,
                    BuildConfig.VERSION_CODE,
                    versionName,
                    Calendar.getInstance().time
                )
            )
        }
        return "Versión " + versionName
    }

}
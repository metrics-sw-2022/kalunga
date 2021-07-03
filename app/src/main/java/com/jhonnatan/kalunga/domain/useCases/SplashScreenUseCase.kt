package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.data.source.local.entities.Version
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 9:26 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenUseCase(private val repository: SplashScreenRepository) {

    suspend fun getAppVersion(): String {
        val versionQuery = repository.queryLast()
        val versionName =
            if (versionQuery.size == 1 && versionQuery.equals(BuildConfig.VERSION_NAME))
                repository.queryLast()[0].versionName
            else
                insertAppVersionDatabase()
        return "Versión " + versionName
    }

    private suspend fun insertAppVersionDatabase(): String {
        val versionName = BuildConfig.VERSION_NAME
        repository.insert(
            Version(
                0,
                BuildConfig.VERSION_CODE,
                versionName,
                Calendar.getInstance().time
            )
        )
        return versionName
    }

    fun getCodePermission(permission: String): Int? {
        return null
    }

}
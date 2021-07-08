package com.jhonnatan.kalunga.domain.injectionOfDependencies

import android.content.Context
import com.jhonnatan.kalunga.data.repositories.source.local.dataBases.KalungaDB
import com.jhonnatan.kalunga.data.repositories.source.local.dataSources.VersionDataSource
import com.jhonnatan.kalunga.data.repositories.VersionRepository

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.injectionOfDependencies
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 1:57 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

object Injection {

    fun providerSplashScreenRepository(context: Context): VersionRepository {
        val database = KalungaDB.getInstance(context)
        val splashScreenDataSource = VersionDataSource.getInstance(database.versionDAO())
        return VersionRepository.getInstance(splashScreenDataSource)
    }
}
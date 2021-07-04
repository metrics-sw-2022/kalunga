package com.jhonnatan.kalunga.domain.injectionOfDependencies

import android.content.Context
import com.jhonnatan.kalunga.data.source.local.dataBases.KalungaDB
import com.jhonnatan.kalunga.data.source.local.dataSources.SplashScreenDataSource
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.injectionOfDependencies
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 1:57 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

object Injection {

    fun providerSplashScreenRepository(context: Context): SplashScreenRepository {
        val database = KalungaDB.getInstance(context)
        val splashScreenDataSource = SplashScreenDataSource.getInstance(database.splashScreenDAO())
        return SplashScreenRepository.getInstance(splashScreenDataSource)
    }
}
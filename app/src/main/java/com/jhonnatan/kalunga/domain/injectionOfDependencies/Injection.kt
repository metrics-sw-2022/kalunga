package com.jhonnatan.kalunga.domain.injectionOfDependencies

import android.content.Context
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.data.room.KalungaDB
import com.jhonnatan.kalunga.data.version.datasource.VersionDataSourceLocal
import com.jhonnatan.kalunga.data.version.repository.VersionRepository
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceRemote

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
        val splashScreenDataSource = VersionDataSourceLocal.getInstance(database.versionDAO())
        return VersionRepository.getInstance(splashScreenDataSource)
    }

    fun providerStartingScreenRepository(): UserRepository {
        val userService = UserDataSourceRemote()
        return UserRepository.getInstance(userService)
    }

}
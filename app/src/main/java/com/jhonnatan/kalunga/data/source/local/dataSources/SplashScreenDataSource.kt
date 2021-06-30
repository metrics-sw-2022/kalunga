package com.jhonnatan.kalunga.data.source.local.dataSources

import androidx.lifecycle.LiveData
import com.jhonnatan.kalunga.data.source.local.dataAccessObject.SplashScreenDAO
import com.jhonnatan.kalunga.data.source.local.entities.Version

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.local.dataSources
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 11:55 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenDataSource private constructor(private val splashScreenDAO: SplashScreenDAO){

    companion object{
        private var INSTANCE: SplashScreenDataSource? = null
        fun getInstance(splashScreenDAO: SplashScreenDAO): SplashScreenDataSource =
            INSTANCE ?: SplashScreenDataSource(splashScreenDAO)
    }

    suspend fun queryLast(): List<Version> = splashScreenDAO.lastVersion()

    suspend fun insert(version: Version) = splashScreenDAO.insertVersion(version)

    suspend fun update(version: Version) = splashScreenDAO.updateVersion(version)

    suspend fun delete(version: Version) = splashScreenDAO.deleteVersion(version)

    suspend fun clear() = splashScreenDAO.clearVersions()
}
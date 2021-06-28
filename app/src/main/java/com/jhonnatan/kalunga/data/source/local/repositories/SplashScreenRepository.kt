package com.jhonnatan.kalunga.data.source.local.repositories

import androidx.lifecycle.LiveData
import com.jhonnatan.kalunga.data.source.local.entities.Version
import com.jhonnatan.kalunga.data.source.local.dataSources.SplashScreenDataSource

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.local.repositories
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 12:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenRepository (private val splashScreenDataSource: SplashScreenDataSource) : SplashScreenRepositoryInterface {

    companion object{
        @Volatile
        private var instance: SplashScreenRepository? = null
        fun getInstance(splashScreenData: SplashScreenDataSource): SplashScreenRepository =
            instance ?: synchronized(this){
                instance ?: SplashScreenRepository(splashScreenData)
            }
    }

    override fun queryLast(): LiveData<Version> {
        return splashScreenDataSource.queryLast()
    }

    override suspend fun insert(version: Version) {
        splashScreenDataSource.insert(version)
    }

    override suspend fun update(version: Version) {
        splashScreenDataSource.update(version)
    }

    override suspend fun delete(version: Version) {
        splashScreenDataSource.delete(version)
    }

    override suspend fun clear() {
        splashScreenDataSource.clear()
    }


}
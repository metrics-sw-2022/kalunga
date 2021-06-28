package com.jhonnatan.kalunga.domain.repositories

import androidx.lifecycle.LiveData
import com.jhonnatan.kalunga.data.source.local.entities.Version
import com.jhonnatan.kalunga.domain.dataSources.VersionDataSource

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.repositories
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 12:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class VersionRepository (private val versionDataSource: VersionDataSource) : VersionRepositoryInterface {

    companion object{
        @Volatile
        private var instance: VersionRepository? = null
        fun getInstance(versionData: VersionDataSource): VersionRepository =
            instance ?: synchronized(this){
                instance ?: VersionRepository(versionData)
            }
    }

    override fun queryLast(): LiveData<Version> {
        return versionDataSource.queryLast()
    }

    override suspend fun insert(version: Version) {
        versionDataSource.insert(version)
    }

    override suspend fun update(version: Version) {
        versionDataSource.update(version)
    }

    override suspend fun delete(version: Version) {
        versionDataSource.delete(version)
    }

    override suspend fun clear() {
        versionDataSource.clear()
    }


}
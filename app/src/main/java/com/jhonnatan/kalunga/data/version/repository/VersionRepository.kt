package com.jhonnatan.kalunga.data.version.repository

import com.jhonnatan.kalunga.data.version.entities.Version
import com.jhonnatan.kalunga.data.version.datasource.VersionDataSourceLocal

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.version.repositories
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 12:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class VersionRepository (private val versionDataSourceLocal: VersionDataSourceLocal) :
    VersionRepositoryInterface {

    companion object{
        @Volatile
        private var instance: VersionRepository? = null
        fun getInstance(versionDataLocal: VersionDataSourceLocal): VersionRepository =
            instance ?: synchronized(this){
                instance ?: VersionRepository(versionDataLocal)
            }
    }

    override suspend fun queryLast(): List<Version> {
        return versionDataSourceLocal.queryLast()
    }

    override suspend fun insert(version: Version) {
        versionDataSourceLocal.insert(version)
    }

    override suspend fun update(version: Version) {
        versionDataSourceLocal.update(version)
    }

    override suspend fun delete(version: Version) {
        versionDataSourceLocal.delete(version)
    }

    override suspend fun clear() {
        versionDataSourceLocal.clear()
    }


}
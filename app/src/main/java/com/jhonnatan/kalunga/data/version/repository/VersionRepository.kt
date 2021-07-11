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

    override suspend fun queryLastVersionLocal(): List<Version> {
        return versionDataSourceLocal.queryLastVersion()
    }

    override suspend fun insertVersionLocal(version: Version) {
        versionDataSourceLocal.insertVersion(version)
    }

    override suspend fun updateVersionLocal(version: Version) {
        versionDataSourceLocal.updateVersion(version)
    }

    override suspend fun deleteVersionLocal(version: Version) {
        versionDataSourceLocal.deleteVersion(version)
    }

    override suspend fun clearVersionsLocal() {
        versionDataSourceLocal.clearVersions()
    }


}
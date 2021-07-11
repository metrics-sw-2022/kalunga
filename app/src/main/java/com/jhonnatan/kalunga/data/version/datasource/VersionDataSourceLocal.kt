package com.jhonnatan.kalunga.data.version.datasource

import com.jhonnatan.kalunga.data.version.source.VersionDAO
import com.jhonnatan.kalunga.data.version.entities.Version

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.local.dataSources
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 11:55 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class VersionDataSourceLocal private constructor(private val versionDAO: VersionDAO){

    companion object{
        private var INSTANCE: VersionDataSourceLocal? = null
        fun getInstance(versionDAO: VersionDAO): VersionDataSourceLocal =
            INSTANCE ?: VersionDataSourceLocal(versionDAO)
    }

    suspend fun queryLastVersion(): List<Version> = versionDAO.lastVersion()

    suspend fun insertVersion(version: Version) = versionDAO.insertVersion(version)

    suspend fun updateVersion(version: Version) = versionDAO.updateVersion(version)

    suspend fun deleteVersion(version: Version) = versionDAO.deleteVersion(version)

    suspend fun clearVersions() = versionDAO.clearVersions()
}
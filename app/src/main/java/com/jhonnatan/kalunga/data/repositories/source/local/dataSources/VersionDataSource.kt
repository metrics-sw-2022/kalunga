package com.jhonnatan.kalunga.data.repositories.source.local.dataSources

import com.jhonnatan.kalunga.data.repositories.source.local.dataAccessObject.VersionDAO
import com.jhonnatan.kalunga.data.entities.Version

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.repositories.source.local.dataSources
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 11:55 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class VersionDataSource private constructor(private val versionDAO: VersionDAO){

    companion object{
        private var INSTANCE: VersionDataSource? = null
        fun getInstance(versionDAO: VersionDAO): VersionDataSource =
            INSTANCE ?: VersionDataSource(versionDAO)
    }

    suspend fun queryLast(): List<Version> = versionDAO.lastVersion()

    suspend fun insert(version: Version) = versionDAO.insertVersion(version)

    suspend fun update(version: Version) = versionDAO.updateVersion(version)

    suspend fun delete(version: Version) = versionDAO.deleteVersion(version)

    suspend fun clear() = versionDAO.clearVersions()
}
package com.jhonnatan.kalunga.data.repositories.source.local.dataAccessObject

import androidx.room.*
import com.jhonnatan.kalunga.data.repositories.source.local.entities.Version

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.repositories.source.local.dataAccessObject
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 11:10 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

@Dao
interface VersionDAO {
    @Query("SELECT * FROM version ORDER BY versionCode DESC LIMIT 1")
    suspend fun lastVersion(): List<Version>

    @Insert
    suspend fun insertVersion(version: Version)

    @Update
    suspend fun updateVersion(version: Version)

    @Delete
    suspend fun deleteVersion(version: Version)

    @Query("DELETE FROM version")
    suspend fun clearVersions()
}
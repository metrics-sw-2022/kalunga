package com.jhonnatan.kalunga.data.source.local.dataAccessObject

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jhonnatan.kalunga.data.source.local.entities.Version

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.local.dataAccessObject
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 11:10 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

interface VersionDAO {
    @Query("SELECT MAX(versionCode) FROM version")
    fun lastVersion(): LiveData<Version>

    @Insert
    suspend fun insertVersion(version: Version)

    @Update
    suspend fun updateVersion(version: Version)

    @Delete
    suspend fun deleteVersion(version: Version)

    @Query("DELETE FROM version")
    suspend fun clearVersions()
}
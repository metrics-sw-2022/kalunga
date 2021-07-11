package com.jhonnatan.kalunga.data.version.repository

import com.jhonnatan.kalunga.data.version.entities.Version

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.version.repositories
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 12:06 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

interface VersionRepositoryInterface {

    suspend fun queryLastVersionLocal(): List<Version>

    suspend fun insertVersionLocal(version: Version)

    suspend fun updateVersionLocal(version: Version)

    suspend fun deleteVersionLocal(version: Version)

    suspend fun clearVersionsLocal()
}
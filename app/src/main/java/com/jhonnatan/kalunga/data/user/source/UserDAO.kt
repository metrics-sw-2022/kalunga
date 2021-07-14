package com.jhonnatan.kalunga.data.user.source

import androidx.room.*
import com.jhonnatan.kalunga.data.user.entities.User
import com.jhonnatan.kalunga.data.version.entities.Version

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.local.dataAccessObject
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 11:10 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

@Dao
interface UserDAO {
    @Query("SELECT * FROM User WHERE account = :account")
    suspend fun getUserByAccount(account: String): List<User>

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user")
    suspend fun clearUsers()
}
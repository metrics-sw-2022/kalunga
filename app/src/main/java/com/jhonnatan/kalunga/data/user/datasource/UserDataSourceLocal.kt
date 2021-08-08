package com.jhonnatan.kalunga.data.user.datasource

import com.jhonnatan.kalunga.data.user.entities.User
import com.jhonnatan.kalunga.data.user.source.UserDAO

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.user.datasource
 * Created by Jhonnatan E. Zamudio P. on 11/07/2021 at 3:19 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class UserDataSourceLocal private constructor(private val userDAO: UserDAO){

    companion object {
        private var INSTANCE: UserDataSourceLocal? = null
        fun getInstance(userDAO: UserDAO): UserDataSourceLocal =
            INSTANCE ?: UserDataSourceLocal(userDAO)
    }

    suspend fun getAllUsers(): List<User> = userDAO.getAllUsers()

    suspend fun getUserByAccount(account: String): List<User> = userDAO.getUserByAccount(account)

    suspend fun insertUser(user: User) = userDAO.insertUser(user)

    suspend fun updateUser(user: User) = userDAO.updateUser(user)

    suspend fun deleteUser(user: User) = userDAO.deleteUser(user)

    suspend fun clearUsers() = userDAO.clearUsers()
}
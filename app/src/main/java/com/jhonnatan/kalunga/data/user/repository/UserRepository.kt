package com.jhonnatan.kalunga.data.user.repository

import com.jhonnatan.kalunga.data.user.entities.RequestUsers
import com.jhonnatan.kalunga.data.user.entities.RequestUsersUpdate
import com.jhonnatan.kalunga.data.user.entities.ResponseUsers
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceRemote

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.version.repositories
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 11:04 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class UserRepository(private val userDataSourceRemote: UserDataSourceRemote) : UserRepositoryInterface {

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(userDataSourceRemote: UserDataSourceRemote): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDataSourceRemote)
            }
    }

    override suspend fun getUsersRemote(): List<ResponseUsers> {
        return userDataSourceRemote.getUsers()
    }

    override suspend fun getUserByAccountRemote(account: String): List<ResponseUsers> {
        return userDataSourceRemote.getUserByAccount(account)
    }

    override suspend fun insertUser(requestUsers: RequestUsers): List<ResponseUsers> {
        return userDataSourceRemote.insertUser(requestUsers)
    }

    override suspend fun updateUser(account: String, requestUsersUpdate: RequestUsersUpdate): List<ResponseUsers> {
        return userDataSourceRemote.updateUser(account, requestUsersUpdate)
    }

    override suspend fun deleteUser(account: String): List<ResponseUsers> {
        return userDataSourceRemote.deleteUser(account)
    }

}
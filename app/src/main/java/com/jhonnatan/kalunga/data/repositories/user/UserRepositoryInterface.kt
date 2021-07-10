package com.jhonnatan.kalunga.data.repositories.user

import com.jhonnatan.kalunga.data.source.remote.entities.requests.RequestUsers
import com.jhonnatan.kalunga.data.source.remote.entities.requests.RequestUsersUpdate
import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseUsers

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.repositories.user
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 11:15 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

interface UserRepositoryInterface {

    suspend fun getUserByAccountRemote(account: String): List<ResponseUsers>

    suspend fun getUsersRemote(): List<ResponseUsers>

    suspend fun insertUser(requestUsers: RequestUsers): List<ResponseUsers>

    suspend fun updateUser(account: String,requestUsersUpdate: RequestUsersUpdate): List<ResponseUsers>
}
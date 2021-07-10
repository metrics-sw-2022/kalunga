package com.jhonnatan.kalunga.data.source.remote.services

import com.jhonnatan.kalunga.data.source.remote.entities.requests.RequestUsers
import com.jhonnatan.kalunga.data.source.remote.entities.requests.RequestUsersUpdate
import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseUsers
import com.jhonnatan.kalunga.data.source.remote.interfaces.UserApiClient
import com.jhonnatan.kalunga.data.source.remote.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.remote.services
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 10:45 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class UserService {

    private val retrofit = RetrofitHelper.getRetrofit()
    val response = retrofit.create(UserApiClient::class.java)

    suspend fun getUsers():List<ResponseUsers>{
        return withContext(Dispatchers.IO){
            response.getAllUsers().body() ?: emptyList()
        }
    }

    suspend fun getUserByAccount(account: String):List<ResponseUsers>{
        return withContext(Dispatchers.IO) {
            response.getUserByAccount(account).body() ?: emptyList()
        }
    }

    suspend fun insertUser(requestUsers: RequestUsers):List<ResponseUsers>{
        return withContext(Dispatchers.IO){
            response.insertUser(requestUsers).body() ?: emptyList()
        }
    }

    suspend fun updateUser(account: String, requestUsersUpdate: RequestUsersUpdate): List<ResponseUsers>{
        return withContext(Dispatchers.IO){
            response.updateUser(account,requestUsersUpdate).body() ?: emptyList()
        }
    }

    suspend fun deleteUser(account: String): List<ResponseUsers>{
        return withContext(Dispatchers.IO){
            response.deleteUser(account).body() ?: emptyList()
        }
    }
}
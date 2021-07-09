package com.jhonnatan.kalunga.data.source.remote.services

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

    suspend fun getUserByAccount(account: String):List<ResponseUsers>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UserApiClient::class.java).getUserByAccount(account)
            response.body() ?: emptyList()
        }
    }
}
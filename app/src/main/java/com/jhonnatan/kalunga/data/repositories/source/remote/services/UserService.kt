package com.jhonnatan.kalunga.data.repositories.source.remote.services

import com.jhonnatan.kalunga.data.repositories.source.remote.entities.responses.ResponseQueryByAccountUser
import com.jhonnatan.kalunga.data.repositories.source.remote.interfaces.UserApiClient
import com.jhonnatan.kalunga.domain.common.utils.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.repositories.source.remote.services
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 10:45 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class UserService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getUserByAccount(account: String):List<ResponseQueryByAccountUser>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UserApiClient::class.java).getUserByAccount(account)
            response.body() ?: emptyList()
        }
    }
}
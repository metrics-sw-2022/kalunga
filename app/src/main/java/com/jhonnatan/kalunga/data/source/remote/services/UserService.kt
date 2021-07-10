package com.jhonnatan.kalunga.data.source.remote.services

import android.R
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseError
import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseUsers
import com.jhonnatan.kalunga.data.source.remote.interfaces.UserApiClient
import com.jhonnatan.kalunga.data.source.remote.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter


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
}
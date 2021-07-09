package com.jhonnatan.kalunga.data.source.remote.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.common.utils
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 9:26 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

object RetrofitHelper {
    fun getRetrofit():Retrofit {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .baseUrl("https://venecambios-kalunga.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
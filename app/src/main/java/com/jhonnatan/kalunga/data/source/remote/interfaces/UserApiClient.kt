package com.jhonnatan.kalunga.data.source.remote.interfaces

import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseQueryByAccountUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.remote.interfaces
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 10:38 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

interface UserApiClient {
    @GET("users/{account}")
    suspend fun getUserByAccount(@Path("account") account: String): Response<List<ResponseQueryByAccountUser>>

}
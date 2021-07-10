package com.jhonnatan.kalunga.data.source.remote.interfaces

import com.jhonnatan.kalunga.data.source.remote.entities.requests.RequestUsers
import com.jhonnatan.kalunga.data.source.remote.entities.requests.RequestUsersUpdate
import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseUsers
import retrofit2.Response
import retrofit2.http.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.remote.interfaces
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 10:38 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

interface UserApiClient {

    @GET("users")
    suspend fun getAllUsers(): Response<List<ResponseUsers>>

    @GET("users/{account}")
    suspend fun getUserByAccount(@Path("account") account: String): Response<List<ResponseUsers>>

    @Headers("Content-Type: application/json")
    @POST("users/new")
    suspend fun insertUser(@Body requestUsers: RequestUsers): Response<List<ResponseUsers>>

    @Headers("Content-Type: application/json")
    @PUT("users/update/{account}")
    suspend fun updateUser(@Path("account") account: String,@Body requestUsersUpdate: RequestUsersUpdate): Response<List<ResponseUsers>>

}
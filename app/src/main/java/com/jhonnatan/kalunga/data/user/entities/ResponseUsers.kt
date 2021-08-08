package com.jhonnatan.kalunga.data.user.entities

import com.google.gson.annotations.SerializedName

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.remote.entities.responses
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 10:35 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

data class ResponseUsers(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: List<UserRemote>?,
)

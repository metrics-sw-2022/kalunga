package com.jhonnatan.kalunga.data.user.entities

import com.google.gson.annotations.SerializedName

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.remote.entities
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 10:11 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

data class UserRemote(
    @SerializedName("account") val account: String,
    @SerializedName("login_attempts") val loginAttempts: Int,
    @SerializedName("status_user") val statusUser: Int,
    @SerializedName("type_user") val typeUser: Int,
    @SerializedName("email") val email: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("document_type") val documentType: Int,
    @SerializedName("document_number") val documentNumber: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String
)

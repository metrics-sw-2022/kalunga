package com.jhonnatan.kalunga.data.user.entities

import com.google.gson.annotations.SerializedName
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.remote.entities
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 10:11 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

data class UserRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("account") val account: String,
    @SerializedName("password_user") val passwordUser: String,
    @SerializedName("login_attempts") val loginAttempts: Int,
    @SerializedName("status_user") val statusUser: Int,
    @SerializedName("validation_code") val validationCode: String,
    @SerializedName("pin") val pin: String,
    @SerializedName("session_state") val sessionState: Boolean,
    @SerializedName("session_start") val sessionStart: Date,
    @SerializedName("session_closing") val sessionClosing: Date,
    @SerializedName("registration_date") val registrationDate: Date,
    @SerializedName("type_user") val typeUser: Int,
    @SerializedName("email") val email: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("document_type") val documentType: Int,
    @SerializedName("document_number") val documentNumber: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String
)

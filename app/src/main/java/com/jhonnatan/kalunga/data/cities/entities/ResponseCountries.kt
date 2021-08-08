package com.jhonnatan.kalunga.data.cities.entities

import com.google.gson.annotations.SerializedName
import com.jhonnatan.kalunga.data.user.entities.UserRemote

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.cities.entities
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
data class ResponseCountries(
    @SerializedName("id") val id: String,
    @SerializedName("cod_pais") val codPais: String,
    @SerializedName("pais") val pais: String
)

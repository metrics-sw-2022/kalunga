package com.jhonnatan.kalunga.data.cities.entities

import com.google.gson.annotations.SerializedName
import com.jhonnatan.kalunga.data.user.entities.UserRemote

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.cities.entities
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:40 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
data class ResponseCities(
    @SerializedName("ciudades") val data: List<String>,
    @SerializedName("pais") val pais: String
)

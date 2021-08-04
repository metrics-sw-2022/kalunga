package com.jhonnatan.kalunga.data.typeDocument.entities

import com.google.gson.annotations.SerializedName

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.typeDocument.entities
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:06 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
data class ResponseDocumentType(
    @SerializedName("id") val id: String,
    @SerializedName("descripcion") val description: String,
    @SerializedName("abreviatura") val abbreviate: String,
    @SerializedName("valor") val valor: Int
)
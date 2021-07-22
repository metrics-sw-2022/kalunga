package com.jhonnatan.kalunga.data.typeDocument.repository

import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.typeDocument.repository
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:04 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
interface TypeDocumentRepositoryInterface {

    suspend fun getDataTypeDocument():List<ResponseDocumentType>

}
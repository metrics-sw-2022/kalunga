package com.jhonnatan.kalunga.data.typeDocument.source

import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.typeDocument.source
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:07 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
interface TypeDocumentJSONInterface {

    suspend fun getDataTypeDocument():List<ResponseDocumentType>
}
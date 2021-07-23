package com.jhonnatan.kalunga.data.typeDocument.datasource

import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType
import com.jhonnatan.kalunga.data.typeDocument.source.TypeDocumentJSONInterface

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.typeDocument.datasource
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:06 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class TypeDocumentDataSourceLocal {

    lateinit var typeDocumentJSONInterface: TypeDocumentJSONInterface

    suspend fun getDataTypeDocument(): List<ResponseDocumentType> {
        return typeDocumentJSONInterface.getDataTypeDocument()
    }

}
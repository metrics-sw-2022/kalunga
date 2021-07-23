package com.jhonnatan.kalunga.data.typeDocument.datasource

import com.jhonnatan.kalunga.data.cities.datasource.CitiesDataSourceLocal
import com.jhonnatan.kalunga.data.cities.source.CitiesJSON
import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType
import com.jhonnatan.kalunga.data.typeDocument.source.TypeDocumentJSON

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.typeDocument.datasource
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:06 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class TypeDocumentDataSourceLocal(private val typeDocumentJSON: TypeDocumentJSON) {

    companion object {
        private var INSTANCE: TypeDocumentDataSourceLocal? = null
        fun getInstance(typeDocumentJSON: TypeDocumentJSON): TypeDocumentDataSourceLocal =
            INSTANCE ?: TypeDocumentDataSourceLocal(typeDocumentJSON)
    }

    fun getDataTypeDocument(): List<ResponseDocumentType> {
        return typeDocumentJSON.getDataTypeDocument()
    }

}
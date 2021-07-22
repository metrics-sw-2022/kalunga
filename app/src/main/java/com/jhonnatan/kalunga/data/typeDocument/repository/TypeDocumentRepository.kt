package com.jhonnatan.kalunga.data.typeDocument.repository

import com.jhonnatan.kalunga.data.cities.datasource.CitiesDataSourceLocal
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.typeDocument.datasource.TypeDocumentDataSourceLocal
import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.typeDocument.repository
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 7:04 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class TypeDocumentRepository(private val typeDocumentDataSourceLocal: TypeDocumentDataSourceLocal) :
    TypeDocumentRepositoryInterface {
    companion object{
        @Volatile
        private var instance: TypeDocumentRepository? = null
        fun getInstance(
            typeDocumentDataSourceLocal: TypeDocumentDataSourceLocal
        ): TypeDocumentRepository =
            instance ?: synchronized(this) {
                instance ?: TypeDocumentRepository(typeDocumentDataSourceLocal)
            }
    }

    override suspend fun getDataTypeDocument():List<ResponseDocumentType>{
        return typeDocumentDataSourceLocal.getDataTypeDocument()
    }
}
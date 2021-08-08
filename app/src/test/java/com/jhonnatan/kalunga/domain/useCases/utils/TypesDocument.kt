package com.jhonnatan.kalunga.domain.useCases.utils

import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases.utils
 * Created by Jhonnatan E. Zamudio P. on 7/08/2021 at 9:37 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class TypesDocument {

    fun getList(): List<ResponseDocumentType> {
        return listOf(
            ResponseDocumentType("1", "Cédula de Ciudadanía", "CC", 0),
            ResponseDocumentType("2", "Cédula de Extranjería", "CE", 1),
            ResponseDocumentType("3", "Cédula de Identidad", "CI", 2),
            ResponseDocumentType("4", "Documento Nacional de Identidad", "DNI", 3),
            ResponseDocumentType("5", "Documento Único de Identidad", "DUI", 4),
            ResponseDocumentType("6", "Identificación Oficial", "ID", 5),
            ResponseDocumentType("7", "Pasaporte", "PA", 6),
            ResponseDocumentType("8", "Permiso de Residencia", "PR", 7),
            ResponseDocumentType("9", "Permiso Especial de Permanencia", "PEP", 8),
            ResponseDocumentType("10", "Registro Único de Migrantes Venezolanos", "RUMV", 9),
            ResponseDocumentType("11", "Tarjeta de Residente Permanente", "TRP", 10)
        ).sortedBy { myObject -> myObject.abbreviate }
    }
}
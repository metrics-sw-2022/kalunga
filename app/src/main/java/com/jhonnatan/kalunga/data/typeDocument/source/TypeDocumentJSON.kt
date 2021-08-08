package com.jhonnatan.kalunga.data.typeDocument.source

import android.content.Context
import com.google.gson.Gson
import com.jhonnatan.kalunga.data.json.JSONConverter
import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType
import org.json.JSONArray
import org.json.JSONObject

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.typeDocument.source
 * Created by Laura S. Sarmiento M. on 22/07/2021 at 10:22 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class TypeDocumentJSON(private val context: Context){
    val path: String = "json_type_document.txt"
    lateinit var jsonConverter : JSONConverter
    lateinit var jsonArray : JSONArray

    fun getDataTypeDocument(): List<ResponseDocumentType> {
        val documentTypeList: ArrayList<ResponseDocumentType> = ArrayList()
        jsonConverter = JSONConverter()
        jsonArray = jsonConverter.getData(path,context)
        var data: JSONObject
        for (id in 0 until jsonArray.length()) {
            data = jsonArray.getJSONObject(id)
            documentTypeList.add(Gson().fromJson(data.toString(), ResponseDocumentType::class.java))
        }
        return documentTypeList
    }
}
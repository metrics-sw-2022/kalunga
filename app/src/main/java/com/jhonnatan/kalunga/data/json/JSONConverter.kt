package com.jhonnatan.kalunga.data.json

import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.json
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 8:15 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class JSONConverter {

    lateinit var jsonArray : JSONArray
    fun getData(path: String) : JSONArray {
        val jsonString = getDataTXT(path)
        if (jsonString != null) {
            try {
                jsonArray = JSONArray(JSONObject(jsonString).optString("data"))
            } catch (ignore: Exception) {
            }
        }
        return jsonArray
    }

    fun getDataTXT(path: String): String? {
        val txtFile = File(path)
        var aux: String? = null
        try {
            val bufferedReader = BufferedReader(InputStreamReader(FileInputStream(txtFile)))
            aux = bufferedReader.readLine()
            bufferedReader.close()
        } catch (ignore: Exception) {
        }
        return aux
    }
}
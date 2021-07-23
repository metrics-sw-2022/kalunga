package com.jhonnatan.kalunga.data.json

import android.content.Context
import android.net.Uri
import com.facebook.FacebookSdk.getApplicationContext
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

    var jsonArray : JSONArray = JSONArray()
    fun getData(path: String, context: Context) : JSONArray {
        val jsonString = getDataTXT(path, context)
        if (jsonString != null) {
            try {
                jsonArray = JSONArray(JSONObject(jsonString).optString("data"))
            } catch (ignore: Exception) {
            }
        }
        return jsonArray
    }

    fun getDataTXT(path: String,context: Context): String? {
        var aux: String? = null
        try {
            val bufferedReader = BufferedReader(InputStreamReader(context.assets.open(path)))
            aux = bufferedReader.readLine()
            bufferedReader.close()
        } catch (ignore: Exception) { }
        return aux
    }
}
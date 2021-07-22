package com.jhonnatan.kalunga.data.cities.source

import android.os.Build
import androidx.annotation.RequiresApi
import com.jhonnatan.kalunga.data.cities.entities.ResponseCities
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.json.JSONConverter
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.cities.source
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 8:11 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class CitiesJSON : CitiesJSONInterface {
    val path: String = "src/main/java/com/jhonnatan/kalunga/data/cities/source/json_cities.txt"
    lateinit var jsonConverter : JSONConverter
    lateinit var jsonArray : JSONArray


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override suspend fun getDataCountries(): Any {
        jsonConverter = JSONConverter()
        jsonArray = jsonConverter.getData(path)
        var data: JSONObject
        for (id in 0 until jsonArray.length()) {
            data = jsonArray.getJSONObject(id)
            bu
        }
        return jsonArray.length()
    }

    override suspend fun getDataCitiesByCodeCountry(country: String): List<ResponseCities> {
        TODO("Not yet implemented")
    }

}
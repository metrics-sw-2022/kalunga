package com.jhonnatan.kalunga.data.cities.source
import com.jhonnatan.kalunga.data.cities.entities.ResponseCities
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.json.JSONConverter
import org.json.JSONArray
import org.json.JSONObject
import com.google.gson.Gson

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.cities.source
 * Created by Laura S. Sarmiento M. on 21/07/2021 at 8:11 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class CitiesJSON {
    val path: String = "app/src/main/java/com/jhonnatan/kalunga/data/cities/source/prueba.txt"
    lateinit var jsonConverter : JSONConverter
    lateinit var jsonArray : JSONArray

    fun getDataCountries(): List<ResponseCountries> {
        val countriesList: ArrayList<ResponseCountries> = ArrayList()
        jsonConverter = JSONConverter()
        jsonArray = jsonConverter.getData(path)
        var data: JSONObject
        for (id in 0 until jsonArray.length()) {
            data = jsonArray.getJSONObject(id)
            countriesList.add(Gson().fromJson(data.toString(),ResponseCountries::class.java))
        }
        return countriesList
    }

    fun getDataCitiesByCodeCountry(country: String): List<ResponseCities> {
        val citiesList: ArrayList<ResponseCities> = ArrayList()
        jsonConverter = JSONConverter()
        jsonArray = jsonConverter.getData(path)
        var data: JSONObject
        for (id in 0 until jsonArray.length()) {
            data = jsonArray.getJSONObject(id)
            if (data["pais"]==country) {
                citiesList.add(Gson().fromJson(data.toString(), ResponseCities::class.java))
            }
        }
        return citiesList
    }

}
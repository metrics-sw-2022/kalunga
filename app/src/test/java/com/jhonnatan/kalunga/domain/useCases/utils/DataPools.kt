package com.jhonnatan.kalunga.domain.useCases.utils

import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.ArrayList

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases.utils
 * Created by Jhonnatan E. Zamudio P. on 7/08/2021 at 8:16 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class DataPools {

    private val emails = mutableListOf<String>()
    private val item1 = mutableListOf<Int>()
    private val item2 = mutableListOf<Int>()
    private val item3 = mutableListOf<Int>()
    private val item4 = mutableListOf<Int>()

    fun createData(dataPool: String, type: Int): Any? {
        val pathDataPool =
            "src/test/java/com/jhonnatan/kalunga/domain/useCases/dataPools/signUp/$dataPool.txt"
        val jsonString = getDataTXT(pathDataPool)
        if (jsonString != null) {
            try {
                val dataPools = JSONArray(JSONObject(jsonString).optString("data_pool"))
                if (type == 0) {
                    return generateData(dataPools)
                } else if (type == 1) {
                     return generateMultipleData(dataPools)
                }
            } catch (ignore: Exception) {
            }
        }
        return null
    }

    private fun getDataTXT(path: String): String? {
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

    private fun generateData(dataPool: JSONArray): List<String> {
        var data: JSONObject
        for (id in 0 until dataPool.length()) {
            data = dataPool.getJSONObject(id)
            emails.add(data["email"].toString())
        }
        return emails
    }

    private fun generateMultipleData(dataPool: JSONArray): Array<List<Int>> {
        var data: JSONObject
        for (id in 0 until dataPool.length()) {
            data = dataPool.getJSONObject(id)
            item1.add(data["item_1"] as Int)
            item2.add(data["item_2"] as Int)
            item3.add(data["item_3"] as Int)
            item4.add(data["item_4"] as Int)
        }
        val result: Array<List<Int>> = arrayOf(item1,item2,item3,item4)
        return result
    }

}
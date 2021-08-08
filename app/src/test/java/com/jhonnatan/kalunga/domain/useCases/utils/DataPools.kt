package com.jhonnatan.kalunga.domain.useCases.utils

import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

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

    fun createData(dataPool: String, type: Int, countItems: Int): Any? {
        val pathDataPool =
            "src/test/java/com/jhonnatan/kalunga/domain/useCases/dataPools/$dataPool.txt"
        val jsonString = getDataTXT(pathDataPool)
        if (jsonString != null) {
            try {
                val dataPools = JSONArray(JSONObject(jsonString).optString("data_pool"))
                if (type == 0) {
                    return generateData(dataPools)
                } else if (type == 1) {
                     return generateMultipleData(dataPools,countItems)
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

    private fun generateMultipleData(dataPool: JSONArray, countItems: Int): Array<List<Int>> {
        var data: JSONObject
        val result: Array<List<Int>>
        for (id in 0 until dataPool.length()) {
            data = dataPool.getJSONObject(id)
            if (countItems > 0) item1.add(data["item_1"] as Int) else emptyList<Int>()
            if (countItems > 1) item2.add(data["item_2"] as Int) else emptyList<Int>()
            if (countItems > 2) item3.add(data["item_3"] as Int) else emptyList<Int>()
            if (countItems > 3) item4.add(data["item_4"] as Int) else emptyList<Int>()
        }
        when (countItems) {
            3 -> result = arrayOf(item1,item2,item3)
            4 -> result = arrayOf(item1,item2,item3,item4)
            else -> result = arrayOf(emptyList())
        }
        return result
    }

}
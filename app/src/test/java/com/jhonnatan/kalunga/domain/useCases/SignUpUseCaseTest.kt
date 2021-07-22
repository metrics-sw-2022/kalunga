package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.data.cities.source.CitiesJSON
import com.jhonnatan.kalunga.data.cities.source.CitiesJSONInterface
import io.github.serpro69.kfaker.Faker
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 16/07/2021 at 6:28 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 */
class SignUpUseCaseTest {
    private lateinit var signUpUseCase: SignUpUseCase
    private val faker = Faker()
    private val emails = mutableListOf<String>()
    private val item1 = mutableListOf<Int>()
    private val item2 = mutableListOf<Int>()
    private val item3 = mutableListOf<Int>()
    private val item4 = mutableListOf<Int>()

    private fun createDataPool(dataPool: String, type: Int) {
        val pathDataPool =
            "src/test/java/com/jhonnatan/kalunga/domain/useCases/dataPools/signUp/$dataPool.txt"
        val jsonString = getDataTXT(pathDataPool)
        if (jsonString != null) {
            try {
                val dataPools = JSONArray(JSONObject(jsonString).optString("data_pool"))
                if (type == 0) {
                    generateData(dataPools)
                } else if (type == 1) {
                    generateMultipleData(dataPools)
                }
            } catch (ignore: Exception) {
            }
        }
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

    private fun generateData(dataPool: JSONArray) {
        var data: JSONObject
        for (id in 0 until dataPool.length()) {
            data = dataPool.getJSONObject(id)
            emails.add(data["email"].toString())
        }
    }

    private fun generateMultipleData(dataPool: JSONArray) {
        var data: JSONObject
        for (id in 0 until dataPool.length()) {
            data = dataPool.getJSONObject(id)
            item1.add(data["item_1"] as Int)
            item2.add(data["item_2"] as Int)
            item3.add(data["item_3"] as Int)
            item4.add(data["item_4"] as Int)
        }
    }

    @Before
    fun setup() {
        signUpUseCase = SignUpUseCase()
    }

    @Test
    fun `Caso 01`() {
        val result = signUpUseCase.areFieldsEmpty("")
        assertEquals(true, result)
    }

    @Test
    fun `Caso 02`() {
        val result = signUpUseCase.areFieldsEmpty(faker.animal.name())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 03`() {
        createDataPool("datapool_1", 0)
        for (id in 0 until emails.size) {
            val result = signUpUseCase.isValidEmail(emails[id])
            assertEquals(false, result)
        }
    }

    @Test
    fun `Caso 04`() {
        createDataPool("datapool_2", 0)
        for (id in 0 until emails.size) {
            val result = signUpUseCase.isValidEmail(emails[id])
            assertEquals(true, result)
        }
    }

    @Test
    fun `Caso 05`() {
        val result = signUpUseCase.isValidLong(faker.animal.name().first().toString(), 2)
        assertEquals(false, result)
    }

    @Test
    fun `Caso 06`() {
        val result = signUpUseCase.isValidLong(faker.animal.name(), 2)
        assertEquals(true, result)
    }

    @Test
    fun `Caso 07`() {
        val result = signUpUseCase.isValidLong(faker.animal.name().first().toString(), 5)
        assertEquals(false, result)
    }

    @Test
    fun `Caso 08`() {
        val result = signUpUseCase.isValidLong(
            faker.animal.name() + faker.animal.name() + faker.animal.name(),
            5
        )
        assertEquals(true, result)
    }


    @Test
    fun `Caso 09`() {
        val result = signUpUseCase.arePasswordsEqual(faker.animal.name(), faker.address.city())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 10`() {
        val animal = faker.animal.name()
        val result = signUpUseCase.arePasswordsEqual(animal, animal)
        assertEquals(true, result)
    }

    @Test
    fun `Caso 11`() {
        val result = signUpUseCase.isNumberPair(1)
        assertEquals(false, result)
    }

    @Test
    fun `Caso 12`() {
        val result = signUpUseCase.isNumberPair(2)
        assertEquals(true, result)
    }

    @Test
    fun `Caso 13`() {
        createDataPool("datapool_3", 1)
        for (id in 0 until item1.size) {
            val result =
                signUpUseCase.changeEnableButton(item1[id], item2[id], item3[id], item4[id])
            assertEquals(false, result)
        }
    }

    @Test
    fun `Caso 14`() {
        createDataPool("datapool_4", 1)
        for (id in 0 until item1.size) {
            val result =
                signUpUseCase.changeEnableButton(item1[id], item2[id], item3[id], item4[id])
            assertEquals(true, result)
        }
    }



}
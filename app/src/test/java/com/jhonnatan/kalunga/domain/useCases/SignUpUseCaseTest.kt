package com.jhonnatan.kalunga.domain.useCases

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.jhonnatan.kalunga.domain.models.enumeration.ResponseErrorField
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
class SignUpUseCaseTest() {
    private lateinit var signUpUseCase: SignUpUseCase
    private val faker = Faker()
    private val emails = mutableListOf<String>()

    private fun createDataPool(dataPool:String){
        val pathDataPool =
            "src/test/java/com/jhonnatan/kalunga/domain/useCases/dataPools/signUp/$dataPool.txt"
        val jsonString = getDataTXT(pathDataPool)
        if (jsonString != null){
            try {
                val dataPools = JSONArray(JSONObject(jsonString).optString("data_pool"))
                generateData(dataPools)
            }catch (ignore: Exception){}
        }
    }

    private fun getDataTXT(path: String) :String? {
        val txtFile = File(path)
        var aux:String? = null
        try {
            val bufferedReader = BufferedReader(InputStreamReader(FileInputStream(txtFile)))
            aux = bufferedReader.readLine()
            bufferedReader.close()
        } catch(ignore:Exception){}
        return aux
    }

    private fun generateData(dataPool: JSONArray){
        var data:JSONObject
        for (id in 0 until dataPool.length()){
            data = dataPool.getJSONObject(id)
            emails.add(data["email"].toString())
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
        createDataPool("datapool_1")
        for (id in 0 until emails.size){
            val result = signUpUseCase.isValidEmail(emails[id])
            assertEquals(false, result)
        }
    }

    @Test
    fun `Caso 04`() {
        createDataPool("datapool_2")
        for (id in 0 until emails.size){
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
    fun `Caso 24`() {
        val result = signUpUseCase.changeEnableButton(faker.animal.name(),"",faker.animal.name(),"",ResponseErrorField.DEFAULT.value,faker.animal.name(),faker.animal.name(),faker.animal.name())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 25`() {
        val result = signUpUseCase.changeEnableButton(faker.animal.name(),faker.animal.name(),faker.animal.name(),faker.animal.name(),ResponseErrorField.DEFAULT.value,faker.animal.name(),faker.animal.name(),faker.animal.name())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 26`() {
        val result = signUpUseCase.changeEnableButton(faker.animal.name(),faker.animal.name(),faker.animal.name(),faker.animal.name(),ResponseErrorField.DEFAULT.value,ResponseErrorField.DEFAULT.value,ResponseErrorField.DEFAULT.value,ResponseErrorField.DEFAULT.value)
        assertEquals(true, result)
    }
}
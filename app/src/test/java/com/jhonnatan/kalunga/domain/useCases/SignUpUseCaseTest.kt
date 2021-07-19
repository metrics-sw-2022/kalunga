package com.jhonnatan.kalunga.domain.useCases

import io.github.serpro69.kfaker.Faker
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Laura S. Sarmiento M. on 16/07/2021 at 6:28 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 */
class SignUpUseCaseTest(){
    private lateinit var signUpUseCase: SignUpUseCase
    private val faker = Faker()

    @Before
    fun setup() {
        signUpUseCase = SignUpUseCase()
    }

    @Test
    fun `Caso 01`(){
        val result = signUpUseCase.areFieldsEmpty("")
        assertEquals(true, result)
    }

    @Test
    fun `Caso 02`(){
        val result = signUpUseCase.areFieldsEmpty(faker.animal.name())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 03`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 04`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@")
        assertEquals(false, result)
    }

    @Test
    fun `Caso 05`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@"+faker.animal.name())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 06`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@"+faker.animal.name()+".")
        assertEquals(false, result)
    }

    @Test
    fun `Caso 07`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@"+faker.animal.name()+"."+faker.animal.name().first())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 08`(){
        val result = signUpUseCase.isValidEmail("/"+faker.animal.name()+"@"+faker.animal.name()+"."+faker.animal.name())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 09`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@/"+faker.animal.name()+"."+faker.animal.name())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 10`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@"+faker.animal.name()+"."+faker.animal.name()+"/")
        assertEquals(false, result)
    }

    @Test
    fun `Caso 11`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@"+faker.animal.name()+"."+faker.animal.name()+".")
        assertEquals(false, result)
    }

    @Test
    fun `Caso 12`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@"+faker.animal.name()+"."+faker.animal.name()+"."+faker.animal.name().first())
        assertEquals(false, result)
    }

    @Test
    fun `Caso 13`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@"+faker.animal.name()+"."+faker.animal.name())
        assertEquals(true, result)
    }

    @Test
    fun `Caso 14`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@"+faker.animal.name()+"."+faker.animal.name()+"."+faker.animal.name())
        assertEquals(true, result)
    }

    @Test
    fun `Caso 15`(){
        val result = signUpUseCase.isValidEmail("_"+faker.animal.name()+"-@"+faker.animal.name()+"."+faker.animal.name())
        assertEquals(true, result)
    }

    @Test
    fun `Caso 16`(){
        val result = signUpUseCase.isValidEmail(faker.animal.name()+"@-"+faker.animal.name()+"."+faker.animal.name())
        assertEquals(true, result)
    }

    @Test
    fun `Caso 17`(){
        val result = signUpUseCase.isValidEmail("_"+faker.animal.name()+"-@-"+faker.animal.name()+"."+faker.animal.name())
        assertEquals(true, result)
    }

}
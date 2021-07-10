package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.data.repositories.user.UserRepository
import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseUsers

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 11:28 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class StartingScreenUseCase(private val userRepository: UserRepository) {

    suspend fun getUserByAccountRemote(account: String?): List<Any> {
        val result = userRepository.getUserByAccountRemote(account!!)[0]
        if (result.message.equals("No existe el usuario en la base de datos"))
            return listOf(false)
        return listOf("", "")
    }
}
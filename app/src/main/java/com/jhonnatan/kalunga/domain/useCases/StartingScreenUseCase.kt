package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.data.repositories.user.UserRepository
import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseUsers
import com.jhonnatan.kalunga.domain.models.ResponseCodeServices

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 11:28 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class StartingScreenUseCase(private val userRepository: UserRepository) {

    suspend fun getUserByAccountRemote(account: String?): Any {
        val result = userRepository.getUserByAccountRemote(account!!)
        if(!result.isEmpty()) {
            if (result[0].data != null)
                return listOf(true, result[0].data)
            else if (result[0].message.equals(ResponseCodeServices.USER_DOES_NOT_EXIST_DB.value))
                return listOf(false)
        }
        return listOf(null,ResponseCodeServices.SERVER_ERROR.value)
    }
}
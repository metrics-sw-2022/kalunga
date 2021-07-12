package com.jhonnatan.kalunga.domain.useCases

import com.jhonnatan.kalunga.data.user.entities.User
import com.jhonnatan.kalunga.data.user.entities.UserRemote
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.domain.models.entities.ResponseStartingUseCase
import com.jhonnatan.kalunga.domain.models.enumeration.CodeSessionState
import com.jhonnatan.kalunga.domain.models.enumeration.CodeTypeUser
import com.jhonnatan.kalunga.domain.models.enumeration.ResponseCodeServices

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 11:28 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class StartingScreenUseCase(private val userRepository: UserRepository) {

    suspend fun getUserByAccountRemote(account: String): ResponseStartingUseCase {
        val result = userRepository.getUserByAccountRemote(account)
        if (!result.isEmpty()) {
            if (result[0].data != null)
                return ResponseStartingUseCase(true, result[0].data)
            else if (result[0].message.equals(ResponseCodeServices.USER_DOES_NOT_EXIST_DB.value))
                return ResponseStartingUseCase(false, null)
        }
        return ResponseStartingUseCase(null, ResponseCodeServices.SERVER_ERROR.value)
    }

    suspend fun getUserByAccountLocal(account: String): ResponseStartingUseCase {
        val result = userRepository.getUserByAccountLocal(account)
        if (result.isEmpty())
            return ResponseStartingUseCase(false, null)
        else
            return ResponseStartingUseCase(true, result)
    }

    suspend fun createUserLocal(userRemote: UserRemote){
        userRepository.insertUserLocal(
            User(
                0,
                userRemote.account,
                CodeSessionState.STARTED.code,
                CodeTypeUser.STANDART.code,
                userRemote.email,
                userRemote.fullName,
                userRemote.documentType,
                userRemote.documentNumber,
                userRemote.phoneNumber,
                userRemote.country,
                userRemote.city
            )
        )
    }

    suspend fun updateUserLocal(user: List<User>) {
        user[0].sessionState = true
        userRepository.updateUserLocal(user[0])
    }
}
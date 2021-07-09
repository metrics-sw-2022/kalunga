package com.jhonnatan.kalunga.domain.useCases

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.jhonnatan.kalunga.data.repositories.user.UserRepository

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 11:28 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class StartingScreenUseCase(private val userRepository: UserRepository) {

    suspend fun getUserByAccountRemote(acct: GoogleSignInAccount): Boolean {
        val response = userRepository.getUserByAccountRemote(acct.id!!)
        val response1 = userRepository.getUsersRemote()
        println("La data es: " + response)
        println("La data es: " + response1)
        return false
    }
}
package com.jhonnatan.kalunga.domain.useCases

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.jhonnatan.kalunga.data.repositories.user.UserRepository
import com.jhonnatan.kalunga.data.source.remote.entities.requests.RequestUsers
import com.jhonnatan.kalunga.data.source.remote.entities.requests.RequestUsersUpdate

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
        /*val response2 = userRepository.insertUser(RequestUsers(
            "tecsco20131@gmail.com",
            "Jhotec2013",
            3,
            true,
            0,
            "tecsco20131@gmail.com",
            "Jhonnatan E Zamudio P",
            0,
            "1016055000",
            "+57 311 2949556",
            "CO",
            "Bogotá"
        ))*/
        val response3 = userRepository.updateUser("tecsco20131@gmail.com", RequestUsersUpdate(
            "login_attempts",
            1
        ))
        println("La data es: " + response)
        println("La data es: " + response1)
        println("La data es: " + response3)
        return false
    }
}
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

        return false
    }
}
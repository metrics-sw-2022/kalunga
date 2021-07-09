package com.jhonnatan.kalunga.data.repositories.user

import com.jhonnatan.kalunga.data.source.remote.entities.responses.ResponseQueryByAccountUser
import com.jhonnatan.kalunga.data.source.remote.services.UserService

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.repositories
 * Created by Jhonnatan E. Zamudio P. on 8/07/2021 at 11:04 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class UserRepository(private val userService: UserService) : UserRepositoryInterface {

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(userService: UserService): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userService)
            }
    }

    override suspend fun getUserByAccountRemote(account: String): List<ResponseQueryByAccountUser> {
        return userService.getUserByAccount(account)
    }

}
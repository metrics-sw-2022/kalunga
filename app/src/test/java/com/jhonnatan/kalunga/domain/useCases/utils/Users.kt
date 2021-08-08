package com.jhonnatan.kalunga.domain.useCases.utils

import com.jhonnatan.kalunga.data.user.entities.RequestUsers
import com.jhonnatan.kalunga.data.user.entities.User
import com.jhonnatan.kalunga.data.user.entities.UserRemote
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.domain.models.enumeration.CodeSessionState
import com.jhonnatan.kalunga.domain.models.enumeration.CodeStatusUser
import com.jhonnatan.kalunga.domain.models.enumeration.CodeTypeUser
import com.jhonnatan.kalunga.domain.models.utils.UtilsSecurity
import io.github.serpro69.kfaker.Faker
import java.util.ArrayList

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases.utils
 * Created by Jhonnatan E. Zamudio P. on 7/08/2021 at 7:22 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class Users {

    private val users: MutableList<User> = ArrayList()
    private val faker = Faker()

    suspend fun create(i: Int, userRepository: UserRepository): MutableList<User> {
        for (x in 0 until i) {
            users.add(
                User(
                    x + 1,
                    (x + 1).toString(),
                    false,
                    CodeTypeUser.STANDART.code,
                    faker.internet.email(),
                    faker.name.name(),
                    0,
                    faker.code.asin(),
                    faker.phoneNumber.cellPhone(),
                    faker.address.country(),
                    faker.address.city()
                )
            )
            userRepository.insertUserLocal(users[x])
        }
        return users
    }

    fun cloneServer(): UserRemote {
        return UserRemote(
            "unitTesting@kalunga.com",
            0,
            3,
            3,
            "unitTesting@kalunga.com",
            "Jhonnatan E Zamudio P",
            0,
            "1016055000",
            "+57 311 2949556",
            "Colombia",
            "Bogotá"
        )
    }

    fun createRequest(codeStatusUser: Int): RequestUsers {
        val email = faker.internet.email()
        return RequestUsers(
            email,
            UtilsSecurity().cipherData(faker.animal.name())!!,
            codeStatusUser,
            CodeSessionState.STARTED.code,
            CodeTypeUser.STANDART.code,
            email,
            faker.name.name(),
            0,
            UtilsSecurity().cipherData(faker.chiquito.expressions())!!,
            UtilsSecurity().cipherData(faker.phoneNumber.cellPhone())!!,
            faker.address.country(),
            faker.address.city()
        )
    }

}
package com.jhonnatan.kalunga.domain.useCases.utils

import com.jhonnatan.kalunga.data.version.entities.Version
import com.jhonnatan.kalunga.data.version.repository.VersionRepository
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases.utils
 * Created by Jhonnatan E. Zamudio P. on 7/08/2021 at 8:02 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class Versions {

    suspend fun create(i: Int, versionRepository: VersionRepository) {
        for (x in 1..i) {
            versionRepository.insertVersionLocal(
                Version(
                    0,
                    x,
                    "1.0.$x",
                    Calendar.getInstance().time
                )
            )
        }
    }
}
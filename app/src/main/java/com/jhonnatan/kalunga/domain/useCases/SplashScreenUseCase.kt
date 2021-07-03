package com.jhonnatan.kalunga.domain.useCases

import android.Manifest
import android.content.Context
import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.data.source.local.entities.Version
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository
import com.jhonnatan.kalunga.domain.models.CodePermissions
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 9:26 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenUseCase(private val repository: SplashScreenRepository) {

    suspend fun getAppVersion(): String {
        val versionQuery = repository.queryLast()
        val versionName =
            if (versionQuery.size == 1 && versionQuery.equals(BuildConfig.VERSION_NAME))
                repository.queryLast()[0].versionName
            else
                insertAppVersionDatabase()
        return "Versión $versionName"
    }

    private suspend fun insertAppVersionDatabase(): String {
        val versionName = BuildConfig.VERSION_NAME
        repository.insert(
            Version(0, BuildConfig.VERSION_CODE, versionName, Calendar.getInstance().time)
        )
        return versionName
    }

    fun getCodePermission(permission: String): Int {
        return when (permission) {
            Manifest.permission.WRITE_EXTERNAL_STORAGE -> CodePermissions.WRITE_STORAGE.code
            Manifest.permission.CAMERA -> CodePermissions.CAMERA.code
            else -> CodePermissions.DEFAULT.code
        }
    }

    fun getMessagePermission(permission: String, context: Context): String {
        val message: String
        if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            message = context.getString(R.string.rationale_write_storage)
        else if (permission.equals(Manifest.permission.CAMERA)){
            message = context.getString(R.string.rationale_camera)
        } else {
            message = context.getString(R.string.rationale_default)
        }
        return message
    }

}
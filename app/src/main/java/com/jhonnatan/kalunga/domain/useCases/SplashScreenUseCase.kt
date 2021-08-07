package com.jhonnatan.kalunga.domain.useCases

import android.Manifest
import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.data.version.entities.Version
import com.jhonnatan.kalunga.data.version.repository.VersionRepository
import com.jhonnatan.kalunga.domain.models.enumeration.CodePermissions
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 9:26 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenUseCase(
    val versionRepository: VersionRepository,
    val userRepository: UserRepository
) {

    suspend fun getAppVersion(): String {
        val versionName: String
        val versionQuery = versionRepository.queryLastVersionLocal()
        versionName =
            if (versionQuery.size == 1 && versionQuery[0].versionName == BuildConfig.VERSION_NAME)
                versionQuery[0].versionName
            else
                insertAppVersionDatabase()
        return "Versión $versionName"
    }

    private suspend fun insertAppVersionDatabase(): String {
        val versionName = BuildConfig.VERSION_NAME
        versionRepository.insertVersionLocal(
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
        return when (permission) {
            Manifest.permission.WRITE_EXTERNAL_STORAGE -> context.getString(R.string.rationale_write_storage)
            Manifest.permission.CAMERA -> context.getString(R.string.rationale_camera)
            else -> context.getString(R.string.rationale_default)
        }
    }

    fun shouldBeUpdated(appUpdateInfo: AppUpdateInfo): Boolean {
        return (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE) &&
                appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) ||
                (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
    }

    fun getUserExist(): Boolean? {
        return null
    }

}
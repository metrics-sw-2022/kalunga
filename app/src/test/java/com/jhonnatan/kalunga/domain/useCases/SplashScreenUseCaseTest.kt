package com.jhonnatan.kalunga.domain.useCases

import android.Manifest
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.testing.FakeAppUpdateManager
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task
import com.jhonnatan.kalunga.BuildConfig
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.data.source.local.dataBases.KalungaDB
import com.jhonnatan.kalunga.data.source.local.dataSources.SplashScreenDataSource
import com.jhonnatan.kalunga.data.source.local.entities.Version
import com.jhonnatan.kalunga.data.source.local.repositories.SplashScreenRepository
import com.jhonnatan.kalunga.domain.models.CodePermissions
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.useCases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 9:40 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 */

@RunWith(AndroidJUnit4::class)
@Suppress("NonAsciiCharacters")
@ExperimentalCoroutinesApi
class SplashScreenUseCaseTest() {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private var context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var database: KalungaDB
    private lateinit var splashScreenDataSource: SplashScreenDataSource
    private lateinit var splashScreenRepository: SplashScreenRepository
    private lateinit var splashScreenUseCase: SplashScreenUseCase
    private val permissionWriteStorge = Manifest.permission.WRITE_EXTERNAL_STORAGE
    private val permissionCamera = Manifest.permission.CAMERA
    private val permissionInternet = Manifest.permission.INTERNET
    val fakeAppUpdateManager by lazy { Mockito.spy(FakeAppUpdateManager(context)) }

    private suspend fun createVersions(i: Int) {
        for (x in 1..i) {
            splashScreenRepository.insert(
                Version(
                    0,
                    x,
                    "1.0.$x",
                    Calendar.getInstance().time
                )
            )
        }
    }

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context,
            KalungaDB::class.java
        ).allowMainThreadQueries().build()
        splashScreenDataSource = SplashScreenDataSource.getInstance(database.splashScreenDAO())
        splashScreenRepository = SplashScreenRepository.getInstance(splashScreenDataSource)
        splashScreenUseCase = SplashScreenUseCase(splashScreenRepository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        database.close()
    }

    @Test
    fun `Caso 01`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val result = splashScreenUseCase.getAppVersion()
            assertEquals("Versión " + BuildConfig.VERSION_NAME, result)
        }
    }

    @Test
    fun `Caso 02`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            createVersions(4)
            val result = splashScreenUseCase.getAppVersion()
            assertEquals("Versión 1.0.1", result)
        }
    }

    @Test
    fun `Caso 03`(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            createVersions(1)
            val result = splashScreenUseCase.getAppVersion()
            assertEquals("Versión " + BuildConfig.VERSION_NAME, result)
        }
    }

    @Test
    fun `Caso 04`() {
        val result = splashScreenUseCase.getCodePermission(permissionWriteStorge)
        assertEquals(CodePermissions.WRITE_STORAGE.code,result)
    }

    @Test
    fun `Caso 05`() {
        val result = splashScreenUseCase.getCodePermission(permissionCamera)
        assertEquals(CodePermissions.CAMERA.code,result)
    }

    @Test
    fun `Caso 06`() {
        val result = splashScreenUseCase.getMessagePermission(permissionWriteStorge, context)
        assertEquals(context.getString(R.string.rationale_write_storage),result)
    }

    @Test
    fun `Caso 07`() {
        val result = splashScreenUseCase.getMessagePermission(permissionCamera, context)
        assertEquals(context.getString(R.string.rationale_camera),result)
    }

    @Test
    fun `Caso 08`() {
        val result = splashScreenUseCase.getMessagePermission(permissionInternet, context)
        assertEquals(context.getString(R.string.rationale_default),result)
    }

    @Test
    fun `Caso 09`() {
        fakeAppUpdateManager.setUpdateAvailable(UpdateAvailability.UPDATE_AVAILABLE, AppUpdateType.IMMEDIATE)
        val result = splashScreenUseCase.shouldBeUpdated(fakeAppUpdateManager)
        assertEquals(true,result)
    }

    @Test
    fun `Caso 10`() {
        fakeAppUpdateManager.downloadStarts()
        fakeAppUpdateManager.setUpdateAvailable(UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
        val result = splashScreenUseCase.shouldBeUpdated(fakeAppUpdateManager)
        assertEquals(true,result)
    }

}
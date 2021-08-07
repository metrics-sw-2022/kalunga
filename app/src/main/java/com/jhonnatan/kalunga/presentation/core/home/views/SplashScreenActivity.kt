package com.jhonnatan.kalunga.presentation.core.home.views

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivitySplashScreenBinding
import com.jhonnatan.kalunga.domain.models.enumeration.CodeActivityForResult
import com.jhonnatan.kalunga.domain.models.enumeration.CodePermissions
import com.jhonnatan.kalunga.domain.models.enumeration.CodeSnackBarCloseAction
import com.jhonnatan.kalunga.domain.models.enumeration.TypeSnackBar
import com.jhonnatan.kalunga.presentation.core.home.viewModels.SplashScreenViewModel
import com.jhonnatan.kalunga.presentation.core.home.viewModels.SplashScreenViewModelFactory
import com.jhonnatan.kalunga.presentation.core.utils.CustomSnackBar
import com.jhonnatan.kalunga.presentation.features.dashboard.views.DashboardActivity
import kotlinx.coroutines.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.Core.Home.SplashScreen.Presentation.ViewModel
 * Created by Jhonnatan E. Zamudio P. on 27/06/2021 at 8:59 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

@DelicateCoroutinesApi
class SplashScreenActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var viewModel: SplashScreenViewModel
    private lateinit var binding: ActivitySplashScreenBinding
    private var appUpdateManager: AppUpdateManager? = null
    private val tag = "SplashScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Kalunga)
        super.onCreate(savedInstanceState)
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        val viewModelFactory = SplashScreenViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SplashScreenViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        binding.lifecycleOwner = this
        binding.vModel = viewModel

        viewModel.loading.observe(this, {
            lifecycleScope.launch(Dispatchers.IO) {
                animationLoading(binding.imageViewLoading, it)
            }
        })

        viewModel.validatePermissions.observe(this, {
            if (it.equals(true))
                viewModel.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        })

        viewModel.requestPermission.observe(this, {
            EasyPermissions.requestPermissions(
                this,
                viewModel.messagePermission.value!!,
                viewModel.codPermission.value!!,
                viewModel.typePermission.value
            )
        })

        viewModel.snackBarTextCloseApp.observe(this, {
            CustomSnackBar().showSnackBar(
                it,
                binding.layoutContain,
                TypeSnackBar.CLOSE_APP.code,
                this,
                CodeSnackBarCloseAction.NONE.code
            )
        })

        viewModel.isConected.observe(this, {
            if (it.equals(true)) {
                val appUpdateInfoTask = appUpdateManager!!.appUpdateInfo
                if (appUpdateInfoTask.isSuccessful)
                    appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
                        viewModel.checkUpdate(
                            appUpdateInfo
                        )
                    }
                else
                    validateUserSession()
                viewModel.validateUserSession()
            } else {
                viewModel.loading.postValue(false)
                viewModel.snackBarTextCloseApp.postValue(getString(R.string.sin_conexion))
            }
        })

        viewModel.startUpdateFlow.observe(this, {
            if (it.equals(true))
                starUpdateFlow()
            else
                viewModel.validateUserSession()
        })
    }

    private fun validateUserSession() {
        if (viewModel.validateUserSession())
            gotoActivity(Intent(this@SplashScreenActivity, DashboardActivity::class.java))
        else
            gotoActivity(Intent(this@SplashScreenActivity, StartingScreenActivity::class.java))
    }

    private fun gotoActivity(activity: Intent) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                delay(500)
                viewModel.loading.postValue(false)
                val intent = activity
                startActivity(intent)
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                finish()
            }
        }
    }


    private fun starUpdateFlow() {
        try {
            appUpdateManager!!.startUpdateFlowForResult(
                viewModel.appUpdateInfoPlayStore.value!!,
                AppUpdateType.IMMEDIATE,
                this,
                CodeActivityForResult.IMMEDIATE_APP_UPDATE_REQ_CODE.code
            )
        } catch (e: SendIntentException) {
            Log.e(tag, "updateAppError:" + e.printStackTrace())
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CodeActivityForResult.IMMEDIATE_APP_UPDATE_REQ_CODE.code) {
            when (resultCode) {
                RESULT_CANCELED -> {
                    Toast.makeText(
                        this,
                        getString(R.string.actualizacion_cancelada),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
                RESULT_OK -> {
                    Toast.makeText(
                        this,
                        getString(R.string.actualizacion_exitosa),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.actualizacion_fallida),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    viewModel.checkOnline(this)
                }
            }
        }
    }

    private fun animationLoading(imageViewLoading: ImageView, state: Boolean) {
        val animation = if (state)
            R.anim.loading
        else
            R.anim.invisible
        imageViewLoading.startAnimation(AnimationUtils.loadAnimation(this, animation))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d(
            tag,
            getString(R.string.on_permissions_granted) + requestCode + getString(R.string.double_point) + perms.size
        )
        when (requestCode) {
            CodePermissions.WRITE_STORAGE.code -> viewModel.hasPermission(
                this,
                Manifest.permission.CAMERA
            )
            CodePermissions.CAMERA.code -> viewModel.checkOnline(this)
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d(
            tag,
            getString(R.string.permission_denied) + requestCode + getString(R.string.double_point) + perms.size
        )
        viewModel.loading.postValue(false)
        viewModel.snackBarTextCloseApp.postValue(getString(R.string.permisos_denegados))
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms))
            AppSettingsDialog.Builder(this).build().show()
    }
}
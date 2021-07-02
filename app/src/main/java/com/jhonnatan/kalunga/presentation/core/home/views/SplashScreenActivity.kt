package com.jhonnatan.kalunga.presentation.core.home.views

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivitySplashScreenBinding
import com.jhonnatan.kalunga.domain.common.snackBars.CustomSnackBar
import com.jhonnatan.kalunga.domain.common.utils.UtilsNetwork
import com.jhonnatan.kalunga.domain.models.CodePermissions
import com.jhonnatan.kalunga.presentation.core.home.viewModels.SplashScreenViewModel
import com.jhonnatan.kalunga.presentation.core.home.viewModels.SplashScreenViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    private val TAG = "SplashScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Kalunga)
        super.onCreate(savedInstanceState)
        val viewModelFactory = SplashScreenViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SplashScreenViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        binding.lifecycleOwner = this
        binding.vModel = viewModel

        viewModel.loading.observe(this, {
            with(binding) {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (it.equals(true))
                        startLoading(imageViewLoading)
                    else
                        stopLoading(imageViewLoading)
                }
            }
        })

        viewModel.validatePermissions.observe(this, {
            lifecycleScope.launch(Dispatchers.IO) {
                if (it.equals(true))
                    validatePermission(
                        R.string.rationale_write_storage, CodePermissions.WRITE_STORAGE.code,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
            }
        })
    }

    private fun stopLoading(imageViewLoading: ImageView) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.invisible)
        imageViewLoading.startAnimation(animation)
    }

    private fun startLoading(imageViewLoading: ImageView) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.loading)
        imageViewLoading.startAnimation(animation)
    }

    private fun validatePermission(message: Int, code: Int, permission: String) {
        when (hasPermission(permission)) {
            true -> {
                when (code) {
                    CodePermissions.CAMERA.code -> checkUpdate()
                    CodePermissions.WRITE_STORAGE.code -> validatePermission(
                        R.string.rationale_camera,
                        CodePermissions.CAMERA.code, Manifest.permission.CAMERA
                    )
                }
            }
            false -> EasyPermissions.requestPermissions(this, getString(message), code, permission)
        }
    }

    private fun hasPermission(permission: String): Boolean {
        return EasyPermissions.hasPermissions(this, permission)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode, permissions, grantResults,
            this
        )
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size)
        when (requestCode) {
            CodePermissions.WRITE_STORAGE.code -> validatePermission(
                R.string.rationale_camera,
                CodePermissions.CAMERA.code, Manifest.permission.CAMERA
            )
            CodePermissions.CAMERA.code -> checkUpdate()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size)
        viewModel.loading.postValue(false)
        CustomSnackBar().showSnackBar(
            resources.getString(R.string.permisos_denegados),
            binding.layoutContain
        )
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms))
            AppSettingsDialog.Builder(this).build().show()
    }

    fun checkUpdate() {
        if (UtilsNetwork().isOnline(this))
            //CustomSnackBar().showSnackBar("Hola", binding.layoutContain)
        else {
            viewModel.loading.postValue(false)
            CustomSnackBar().showSnackBar(
                resources.getString(R.string.sin_conexion),
                binding.layoutContain
            )
        }
    }
}
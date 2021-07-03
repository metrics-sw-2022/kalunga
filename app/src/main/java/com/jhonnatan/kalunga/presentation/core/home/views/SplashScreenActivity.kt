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
import com.jhonnatan.kalunga.presentation.core.utils.CustomSnackBar
import com.jhonnatan.kalunga.domain.models.CodePermissions
import com.jhonnatan.kalunga.domain.models.TypeSnackBar
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
            lifecycleScope.launch(Dispatchers.IO) {
                animationLoading(binding.imageViewLoading, it)
            }
        })

        viewModel.validatePermissions.observe(this, {
            if (it.equals(true))
                viewModel.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        })

        viewModel.hasPermission.observe(this, {
            when (it) {
                true -> {
                    viewModel.stateCode.postValue(true)
                }
                false -> EasyPermissions.requestPermissions(
                    this,
                    viewModel.messagePermission.value!!,
                    viewModel.codPermission.value!!,
                    viewModel.typePermission.value
                )
            }
        })

        viewModel.stateCode.observe(this, {
            if (it.equals(true)) {
                when (viewModel.codPermission.value) {
                    CodePermissions.CAMERA.code -> viewModel.checkOnline(this)
                    CodePermissions.WRITE_STORAGE.code -> viewModel.hasPermission(
                        this,
                        Manifest.permission.CAMERA
                    )
                }
            }
        })

        viewModel.snackBarTextCloseApp.observe(this, {
            CustomSnackBar().showSnackBar(
                it,
                binding.layoutContain,
                TypeSnackBar.CLOSE_APP.code,
                this
            )
        })

        viewModel.isConected.observe(this, {
            if (it.equals(true))
            //co
            else {
                viewModel.loading.postValue(false)
                viewModel.snackBarTextCloseApp.postValue(resources.getString(R.string.sin_conexion))
            }
        })
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
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size)
        when (requestCode) {
            CodePermissions.WRITE_STORAGE.code -> viewModel.hasPermission(
                this,
                Manifest.permission.CAMERA
            )
            CodePermissions.CAMERA.code -> viewModel.checkOnline(this)
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size)
        viewModel.loading.postValue(false)
        viewModel.snackBarTextCloseApp.postValue(resources.getString(R.string.permisos_denegados))
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms))
            AppSettingsDialog.Builder(this).build().show()
    }
}
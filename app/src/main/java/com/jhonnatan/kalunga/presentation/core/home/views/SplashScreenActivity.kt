package com.jhonnatan.kalunga.presentation.core.home.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.presentation.core.home.viewModels.SplashScreenViewModel
import com.jhonnatan.kalunga.databinding.ActivitySplashScreenBinding
import com.jhonnatan.kalunga.presentation.core.home.viewModels.SplashScreenViewModelFactory

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.Core.Home.SplashScreen.Presentation.ViewModel
 * Created by Jhonnatan E. Zamudio P. on 27/06/2021 at 8:59 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = SplashScreenViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SplashScreenViewModel::class.java]
        val binding: ActivitySplashScreenBinding = DataBindingUtil.setContentView(
            this,R.layout.activity_splash_screen)
        binding.lifecycleOwner = this
        binding.vModel = viewModel

    }
}
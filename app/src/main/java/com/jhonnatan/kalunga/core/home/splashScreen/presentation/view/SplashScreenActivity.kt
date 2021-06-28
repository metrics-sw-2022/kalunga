package com.jhonnatan.kalunga.core.home.splashScreen.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.core.home.splashScreen.presentation.viewModel.SplashScreenViewModel
import com.jhonnatan.kalunga.databinding.ActivitySplashScreenBinding

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.Core.Home.SplashScreen.Presentation.ViewModel
 * Created by Jhonnatan E. Zamudio P. on 27/06/2021 at 8:59 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashScreenBinding = DataBindingUtil.setContentView(
            this,R.layout.activity_splash_screen)
        binding.lifecycleOwner = this
        binding.vModel = viewModel

    }
}
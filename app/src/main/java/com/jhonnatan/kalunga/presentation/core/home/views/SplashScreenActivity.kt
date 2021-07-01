package com.jhonnatan.kalunga.presentation.core.home.views

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivitySplashScreenBinding
import com.jhonnatan.kalunga.presentation.core.home.viewModels.SplashScreenViewModel
import com.jhonnatan.kalunga.presentation.core.home.viewModels.SplashScreenViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.Core.Home.SplashScreen.Presentation.ViewModel
 * Created by Jhonnatan E. Zamudio P. on 27/06/2021 at 8:59 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

@DelicateCoroutinesApi
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = SplashScreenViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SplashScreenViewModel::class.java]
        val binding: ActivitySplashScreenBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_splash_screen
        )
        binding.lifecycleOwner = this
        binding.vModel = viewModel

        viewModel.loading.observe(this, {
            with(binding) {
                lifecycleScope.launch(Dispatchers.IO) {
                    if (it.equals(true)) {
                        startLoading(imageViewLoading)
                    } else {
                        stopLoading(imageViewLoading)
                    }
                }
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
}
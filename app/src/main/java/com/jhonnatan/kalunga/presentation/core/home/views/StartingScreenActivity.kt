package com.jhonnatan.kalunga.presentation.core.home.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityStartingScreenBinding
import com.jhonnatan.kalunga.presentation.core.home.viewModels.StartingScreenViewModel
import com.jhonnatan.kalunga.presentation.core.home.viewModels.StartingScreenViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class StartingScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: StartingScreenViewModel
    private lateinit var binding: ActivityStartingScreenBinding
    private val TAG = "StartingScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = StartingScreenViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[StartingScreenViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_starting_screen)
        binding.lifecycleOwner = this
        binding.vModel = viewModel
    }
}
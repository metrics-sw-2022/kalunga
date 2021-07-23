package com.jhonnatan.kalunga.presentation.core.session.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.session.viewModels
 * Created by Laura S. Sarmiento M. on 22/07/2021 at 6:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class ConfigurationViewModel : ViewModel()  {
}

@DelicateCoroutinesApi
@Suppress("UNCHECKED_CAST")
class ConfigurationViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ConfigurationViewModelFactory? = null
        fun getInstance(): ConfigurationViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ConfigurationViewModelFactory()
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfigurationViewModel() as T
    }
}
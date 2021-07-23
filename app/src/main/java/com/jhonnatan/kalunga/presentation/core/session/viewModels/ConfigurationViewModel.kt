package com.jhonnatan.kalunga.presentation.core.session.viewModels

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.cities.repository.CitiesRepository
import com.jhonnatan.kalunga.data.cities.source.CitiesJSON
import com.jhonnatan.kalunga.data.typeDocument.repository.TypeDocumentRepository
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import com.jhonnatan.kalunga.domain.useCases.ConfigurationUseCase
import com.jhonnatan.kalunga.domain.useCases.StartingScreenUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.session.viewModels
 * Created by Laura S. Sarmiento M. on 22/07/2021 at 6:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class ConfigurationViewModel(userRepository: UserRepository, citiesRepository: CitiesRepository, typeDocumentRepository: TypeDocumentRepository) : ViewModel()  {

    lateinit var countriesList : List<ResponseCountries>
    val countriesSpinnerArray: MutableList<String> = ArrayList()
    private val configurationUseCase = ConfigurationUseCase(userRepository, citiesRepository, typeDocumentRepository)

    init {
        fillCountriesSpinner()
    }

    private fun fillCountriesSpinner(){
        viewModelScope.launch {
            countriesList = configurationUseCase.getDataCountries()
            println("countriesList $countriesList")
            for (element in countriesList) {
                countriesSpinnerArray.add(element.pais)
            }
        }
    }
}

@DelicateCoroutinesApi
@Suppress("UNCHECKED_CAST")
class ConfigurationViewModelFactory(private val userRepository: UserRepository, private val citiesRepository: CitiesRepository, private val typeDocumentRepository: TypeDocumentRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ConfigurationViewModelFactory? = null
        fun getInstance(context: Context): ConfigurationViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ConfigurationViewModelFactory(
                    Injection.providerConfigurationUserRepository(context), Injection.providerConfigurationCitiesRepository(), Injection.providerConfigurationTypeDocumentRepository()
                )
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfigurationViewModel(userRepository, citiesRepository, typeDocumentRepository) as T
    }
}
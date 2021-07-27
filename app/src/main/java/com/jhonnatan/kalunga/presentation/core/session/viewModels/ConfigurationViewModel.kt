package com.jhonnatan.kalunga.presentation.core.session.viewModels

import android.content.Context
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jhonnatan.kalunga.data.cities.entities.ResponseCities
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.cities.repository.CitiesRepository
import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType
import com.jhonnatan.kalunga.data.typeDocument.repository.TypeDocumentRepository
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import com.jhonnatan.kalunga.domain.models.entities.UserAccountData
import com.jhonnatan.kalunga.domain.models.enumeration.*
import com.jhonnatan.kalunga.domain.models.utils.UtilsCountry
import com.jhonnatan.kalunga.domain.useCases.ConfigurationUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

/**
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.session.viewModels
 * Created by Laura S. Sarmiento M. on 22/07/2021 at 6:10 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 **/
class ConfigurationViewModel(
    userRepository: UserRepository,
    citiesRepository: CitiesRepository,
    typeDocumentRepository: TypeDocumentRepository
) : ViewModel() {

    lateinit var countriesList: List<ResponseCountries>
    lateinit var typeDocumentsList: List<ResponseDocumentType>
    val countrySelectedPosition = MutableLiveData<Int>()
    val typeDocumentSelectedPosition = MutableLiveData<Int>()
    val numberPhone = MutableLiveData<String>()
    val citiesList: MutableLiveData<ArrayList<ResponseCities>> = MutableLiveData<ArrayList<ResponseCities>>()
    private val configurationUseCase =
        ConfigurationUseCase(userRepository, citiesRepository, typeDocumentRepository)
    private var validIdentification = MutableLiveData<Boolean>()
    private var validPhone = MutableLiveData<Boolean>()
    private var validCity = MutableLiveData<Boolean>()
    var userAccount = MutableLiveData<UserAccountData>()
    var emailValue = MutableLiveData<String>()
    var nameValue = MutableLiveData<String>()
    var passwordValue = MutableLiveData<String>()
    val errorIdentification = MutableLiveData<String>()
    val errorCity = MutableLiveData<String>()
    val errorPhone = MutableLiveData<String>()

    init {
        getCountriesSpinner()
        countrySelectedPosition.value = configurationUseCase.getCountryPosition(CodeCountries.COLOMBIA.value,countriesList)
        getDocumentTypeSpinner()
        typeDocumentSelectedPosition.value = configurationUseCase.getTypeDocumentPosition(CodeTypeDocument.CEDULA_DE_CIUDADANIA.value,typeDocumentsList)

    }

    fun setInitialValues(){
        userAccount.value = UserAccountData(emailValue.value!!,nameValue.value!!,emailValue.value!!,passwordValue.value!!,passwordValue.value!!,"","","")
    }

    private fun getCountriesSpinner() {
        viewModelScope.launch {
            countriesList = configurationUseCase.getDataCountries()
        }
    }

    private fun getDocumentTypeSpinner() {
        viewModelScope.launch {
            typeDocumentsList = configurationUseCase.getDataTypeDocument()
        }
    }

    fun getDataCitiesByCodeCountry() {
        viewModelScope.launch {
            citiesList.value = ArrayList(configurationUseCase.getDataCitiesByCodeCountry(countriesList[countrySelectedPosition.value!!].pais))
        }
    }

    fun formatPhone(text: String) {
        val whiteSpacesList: List<Int>
        if (text.isNotEmpty()) {
            if (!text.last().isWhitespace()) {
                whiteSpacesList = UtilsCountry().getWhiteSpaceList(countriesList[countrySelectedPosition.value!!].pais)
                if (whiteSpacesList.isNotEmpty()) {
                    val formatPhone = configurationUseCase.getFormatPhone(text,whiteSpacesList)
                    if (formatPhone!==text){
                        numberPhone.value = formatPhone
                    }
                }
            }
        }
    }

    fun areFieldsEmpty(text: Editable?, field: Int) {

        if (configurationUseCase.areFieldsEmpty(text.toString())) {
            setErrorText(field, ResponseErrorField.ERROR_EMPTY.value)
            when (field) {
                CodeField.IDENTIFICATION_FIELD.code -> validIdentification.value = false
                CodeField.PHONE_FIELD.code -> validPhone.value = false
                CodeField.CITY_FIELD.code -> validCity.value = false
            }
            //changeEnableButton()
        } else {
            setErrorText(field, ResponseErrorField.DEFAULT.value)
            when (field) {
                CodeField.IDENTIFICATION_FIELD.code -> {
                    userAccount.value!!.identification = text.toString()
                }
                CodeField.PHONE_FIELD.code -> {
                    userAccount.value!!.phone = text.toString()
                    formatPhone(text.toString())
                }
                CodeField.CITY_FIELD.code -> {
                    userAccount.value!!.city = text.toString()
                    //isValidCity(text.toString())
                }
            }
        }
    }

    private fun setErrorText(field: Int, value: String) {
        when (field) {
            CodeField.IDENTIFICATION_FIELD.code -> errorIdentification.value = value
            CodeField.PHONE_FIELD.code -> errorPhone.value = value
            CodeField.CITY_FIELD.code -> errorCity.value = value
        }
    }
}

@DelicateCoroutinesApi
@Suppress("UNCHECKED_CAST")
class ConfigurationViewModelFactory(
    private val userRepository: UserRepository,
    private val citiesRepository: CitiesRepository,
    private val typeDocumentRepository: TypeDocumentRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ConfigurationViewModelFactory? = null
        fun getInstance(context: Context): ConfigurationViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ConfigurationViewModelFactory(
                    Injection.providerConfigurationUserRepository(context),
                    Injection.providerConfigurationCitiesRepository(context),
                    Injection.providerConfigurationTypeDocumentRepository(context)
                )
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfigurationViewModel(userRepository, citiesRepository, typeDocumentRepository) as T
    }
}
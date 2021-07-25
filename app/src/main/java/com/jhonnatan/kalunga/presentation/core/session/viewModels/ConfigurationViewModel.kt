package com.jhonnatan.kalunga.presentation.core.session.viewModels

import android.content.Context
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.cities.repository.CitiesRepository
import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType
import com.jhonnatan.kalunga.data.typeDocument.repository.TypeDocumentRepository
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.domain.injectionOfDependencies.Injection
import com.jhonnatan.kalunga.domain.models.enumeration.CodeCountries
import com.jhonnatan.kalunga.domain.useCases.ConfigurationUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import java.security.spec.MGF1ParameterSpec
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource

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
    val numberFormat = MutableLiveData<String>()
    private val configurationUseCase =
        ConfigurationUseCase(userRepository, citiesRepository, typeDocumentRepository)

    init {
        getCountriesSpinner()
        countrySelectedPosition.value = configurationUseCase.getCountryPosition(CodeCountries.COLOMBIA.value,countriesList)
        getDocumentTypeSpinner()
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

    fun formatPhone(text: Editable) {

    }

    fun encryptInfo(password_user: String, document_number: String, phone_number: String) {
        val plaintext: ByteArray =
            byteArrayOf(password_user.toByte(), document_number.toByte(), phone_number.toByte())
        val keygen = KeyGenerator.getInstance("AES")
        keygen.init(16)
        val key: SecretKey = keygen.generateKey()
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(
            Cipher.ENCRYPT_MODE, key,
            OAEPParameterSpec(
                "SHA-256",
                "MGF1",
                MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT
            )
        )
        val ciphertext: ByteArray = cipher.doFinal(plaintext)
        val iv: ByteArray = cipher.iv
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
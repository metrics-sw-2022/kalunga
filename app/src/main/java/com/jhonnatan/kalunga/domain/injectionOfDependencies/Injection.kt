package com.jhonnatan.kalunga.domain.injectionOfDependencies

import android.content.Context
import android.net.Uri
import com.jhonnatan.kalunga.data.cities.datasource.CitiesDataSourceLocal
import com.jhonnatan.kalunga.data.cities.repository.CitiesRepository
import com.jhonnatan.kalunga.data.cities.source.CitiesJSON
import com.jhonnatan.kalunga.data.room.KalungaDB
import com.jhonnatan.kalunga.data.typeDocument.datasource.TypeDocumentDataSourceLocal
import com.jhonnatan.kalunga.data.typeDocument.repository.TypeDocumentRepository
import com.jhonnatan.kalunga.data.typeDocument.source.TypeDocumentJSON
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceLocal
import com.jhonnatan.kalunga.data.user.datasource.UserDataSourceRemote
import com.jhonnatan.kalunga.data.user.repository.UserRepository
import com.jhonnatan.kalunga.data.version.datasource.VersionDataSourceLocal
import com.jhonnatan.kalunga.data.version.repository.VersionRepository
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.injectionOfDependencies
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 1:57 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

object Injection {

    fun providerSplashScreenRepository(context: Context): VersionRepository {
        val database = KalungaDB.getInstance(context)
        val splashScreenDataSource = VersionDataSourceLocal.getInstance(database.versionDAO())
        return VersionRepository.getInstance(splashScreenDataSource)
    }

    fun providerStartingScreenRepository(context: Context): UserRepository {
        val database = KalungaDB.getInstance(context)
        val userDataSourceRemote = UserDataSourceRemote()
        val userDataSourceLocal = UserDataSourceLocal.getInstance(database.userDAO())
        return UserRepository.getInstance(userDataSourceRemote,userDataSourceLocal)
    }

    fun providerConfigurationUserRepository(context: Context) : UserRepository{
        val database = KalungaDB.getInstance(context)
        val userDataSourceRemote = UserDataSourceRemote()
        val userDataSourceLocal = UserDataSourceLocal.getInstance(database.userDAO())
        return UserRepository.getInstance(userDataSourceRemote,userDataSourceLocal)
    }


    fun providerConfigurationCitiesRepository(context: Context) : CitiesRepository{
        val citiesJSON = CitiesJSON(context)
        val citiesDataSourceLocal = CitiesDataSourceLocal(citiesJSON)
        return CitiesRepository.getInstance(citiesDataSourceLocal)
    }

    fun providerConfigurationTypeDocumentRepository(context: Context) : TypeDocumentRepository{
        val typeDocumentJSON = TypeDocumentJSON(context)
        val typeDocumentDataSourceLocal = TypeDocumentDataSourceLocal(typeDocumentJSON)
        return TypeDocumentRepository.getInstance(typeDocumentDataSourceLocal)
    }

}
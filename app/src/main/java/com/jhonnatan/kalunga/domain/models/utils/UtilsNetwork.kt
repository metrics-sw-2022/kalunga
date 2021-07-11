package com.jhonnatan.kalunga.domain.models.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models.utils
 * Created by Jhonnatan E. Zamudio P. on 2/07/2021 at 12:30 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class UtilsNetwork {

    fun isOnline(context: Context):Boolean{
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                @Suppress("DEPRECATION")
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI)
                        result = true
                    else if (type == ConnectivityManager.TYPE_MOBILE)
                        result = true
                }
            }
        }
        return result
    }

}
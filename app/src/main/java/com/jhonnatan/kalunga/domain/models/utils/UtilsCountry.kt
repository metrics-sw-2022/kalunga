package com.jhonnatan.kalunga.domain.models.utils

import com.jhonnatan.kalunga.R

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models.utils
 * Created by Jhonnatan E. Zamudio P. on 25/07/2021 at 11:56 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class UtilsCountry {
    fun getIdFlag(country: String): Int {
        when (country) {
            "Colombia" -> return R.mipmap.colombia
            "Venezuela" -> return R.mipmap.venezuela
            "España" -> return R.mipmap.espana
            "Italia" -> return R.mipmap.italia
            "Estados Unidos" -> return R.mipmap.estados_unidos
            "Chile" -> return R.mipmap.chile
            "Perú" -> return R.mipmap.peru
            "Ecuador" -> return R.mipmap.ecuador
            else -> return 0
        }
    }
}
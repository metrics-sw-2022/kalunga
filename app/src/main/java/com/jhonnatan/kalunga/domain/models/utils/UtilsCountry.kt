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
        var flag = 0
        when (country) {
            "Colombia" -> flag = R.mipmap.colombia
            "Venezuela" -> flag = R.mipmap.venezuela
            "España" -> flag = R.mipmap.espana
            "Italia" -> flag = R.mipmap.italia
            "Estados Unidos" -> flag = R.mipmap.estados_unidos
            "Chile" -> flag = R.mipmap.chile
            "Perú" -> flag = R.mipmap.peru
            "Ecuador" -> flag = R.mipmap.ecuador
        }
        return flag
    }
}
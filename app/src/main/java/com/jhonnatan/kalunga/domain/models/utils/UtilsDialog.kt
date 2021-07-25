package com.jhonnatan.kalunga.domain.models.utils

import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.domain.models.enumeration.CodeTypeSpinner

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.models.utils
 * Created by Jhonnatan E. Zamudio P. on 25/07/2021 at 1:52 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class UtilsDialog {
    fun getIdTitle(code: Int): Int {
        when (code) {
            CodeTypeSpinner.COUNTRIES.code -> return R.string.país
            CodeTypeSpinner.TYPE_DOCUMENT.code -> return R.string.seleccion_tipo_documento
            else -> return 0
        }
    }
}
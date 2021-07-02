package com.jhonnatan.kalunga.domain.common.snackBars

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar


/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.domain.common.snackBars
 * Created by Jhonnatan E. Zamudio P. on 2/07/2021 at 12:49 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class CustomSnackBar {

    fun showSnackBar(message: String, layoutContain: ConstraintLayout) {
        val mySnackbar = Snackbar.make(layoutContain, message, Snackbar.LENGTH_INDEFINITE)
        mySnackbar.setAction("CERRAR", {
            System.exit(0)
        })
        mySnackbar.setBackgroundTint(Color.rgb(148, 65, 69))
        mySnackbar.show()
    }

}
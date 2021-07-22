package com.jhonnatan.kalunga.presentation.core.utils

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.domain.models.enumeration.TypeSnackBar
import kotlin.system.exitProcess

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.snackBars
 * Created by Jhonnatan E. Zamudio P. on 2/07/2021 at 12:49 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/


class CustomSnackBar {
    fun showSnackBar(message: String, layoutContain: ConstraintLayout, type: Int, context: Context) {
        when(type){
            TypeSnackBar.CLOSE_APP.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), true,
                        context.getColor(R.color.error))
                else
                    Color.RED
            }
            TypeSnackBar.ERROR.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), false,
                        context.getColor(R.color.error))
                else
                    Color.RED
            }
            TypeSnackBar.INFO.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), false,
                        context.getColor(R.color.info))
                else
                    Color.RED
            }
            TypeSnackBar.WARNING.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), false,
                        context.getColor(R.color.warning))
                else
                    Color.RED
            }
            TypeSnackBar.SUCCESS.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), false,
                        context.getColor(R.color.success))
                else
                    Color.RED
            }
        }
    }

    private fun buildSnackBar(layoutContain: ConstraintLayout, message: String, messageAction: String,
        exit: Boolean, rgb: Int) {
        val mySnackbar = Snackbar.make(layoutContain, message, Snackbar.LENGTH_INDEFINITE)
        mySnackbar.setAction(messageAction) {
            if (exit)
                exitProcess(0)
        }
        mySnackbar.setBackgroundTint(rgb)
        mySnackbar.show()
    }

}
package com.jhonnatan.kalunga.presentation.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.domain.models.enumeration.CodeSnackBarCloseAction
import com.jhonnatan.kalunga.domain.models.enumeration.TypeSnackBar
import com.jhonnatan.kalunga.presentation.core.home.views.StartingScreenActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlin.system.exitProcess


/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.snackBars
 * Created by Jhonnatan E. Zamudio P. on 2/07/2021 at 12:49 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/


class CustomSnackBar {
    @DelicateCoroutinesApi
    fun showSnackBar(message: String, layoutContain: ConstraintLayout, type: Int, context: Context, action: Int) {
        when(type){
            TypeSnackBar.CLOSE_APP.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), true,
                        context.getColor(R.color.error),context,action)
                else
                    Color.RED
            }
            TypeSnackBar.ERROR.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), false,
                        context.getColor(R.color.error),context,action)
                else
                    Color.RED
            }
            TypeSnackBar.INFO.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), false,
                        context.getColor(R.color.info),context,action)
                else
                    Color.RED
            }
            TypeSnackBar.WARNING.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), false,
                        context.getColor(R.color.warning),context,action)
                else
                    Color.RED
            }
            TypeSnackBar.SUCCESS.code -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    buildSnackBar(layoutContain, message, context.getString(R.string.cerrar), false,
                        context.getColor(R.color.success),context,action)
                else
                    Color.RED
            }
        }
    }

    @DelicateCoroutinesApi
    private fun buildSnackBar(layoutContain: ConstraintLayout, message: String, messageAction: String,
                              exit: Boolean, rgb: Int, context: Context, action: Int) {
        val mySnackbar = Snackbar.make(layoutContain, message, Snackbar.LENGTH_INDEFINITE)
        mySnackbar.setAction(messageAction) {
            if (exit)
                exitProcess(0)
        }
            .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onShown(transientBottomBar: Snackbar?) {
                    super.onShown(transientBottomBar)
                }

                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    when (action){
                        CodeSnackBarCloseAction.STARTING_ACTIVITY.code -> {
                            val intent= Intent(context, StartingScreenActivity::class.java)
                            context.startActivity(intent)
                            (context as Activity).overridePendingTransition(
                                R.anim.right_in, R.anim.right_out
                            )
                            context.finish()
                        }
                        else->{}
                    }
                }
            })
        mySnackbar.setBackgroundTint(rgb)
        mySnackbar.show()


    }

}
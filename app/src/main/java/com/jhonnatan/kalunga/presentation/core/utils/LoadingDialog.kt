package com.jhonnatan.kalunga.presentation.core.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.jhonnatan.kalunga.R


/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.utils
 * Created by Jhonnatan E. Zamudio P. on 10/07/2021 at 6:46 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class LoadingDialog (val context: Context) {
    private var dialog: AlertDialog? = null

    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(context,R.style.CustomDialog)
        builder.setView(View.inflate(context, R.layout.loading_dialog,null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog!!.show()
    }

    fun hideLoadingDialog(){
        dialog?.dismiss()
    }
}
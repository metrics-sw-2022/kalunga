package com.jhonnatan.kalunga.presentation.core.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.jhonnatan.kalunga.R


/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.utils
 * Created by Jhonnatan E. Zamudio P. on 10/07/2021 at 6:46 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class LoadingDialog (val context: Context, val text: String) {
    private var dialog: AlertDialog? = null

    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(context,R.style.CustomDialog)
        val factory = LayoutInflater.from(context)
        val loadingDialogView : View = factory.inflate(R.layout.loading_dialog, null)
        val textViewLoadingDialog = loadingDialogView.findViewById<TextView>(R.id.textViewLoadingDialog)
        textViewLoadingDialog.setText(text)
        builder.setView(loadingDialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog!!.show()
    }

    fun hideLoadingDialog(){
        dialog?.dismiss()
    }
}
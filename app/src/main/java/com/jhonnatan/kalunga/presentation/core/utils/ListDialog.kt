package com.jhonnatan.kalunga.presentation.core.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.databinding.DialogListBinding
import com.jhonnatan.kalunga.domain.models.utils.UtilsDialog

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.session.views
 * Created by Jhonnatan E. Zamudio P. on 24/07/2021 at 12:31 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class ListDialog(
    private val data: List<Any>,
    private val code: Int,
    private var customActionSpinner: CustomSpinnerAdapter.CustomActionSpinner
): DialogFragment() {
    private var mBinding: DialogListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_list, null, false)
        val alertDialog = AlertDialog.Builder(activity,R.style.CustomDialogTheme)
        alertDialog.setView(mBinding?.root)
        alertDialog.setTitle(UtilsDialog().getIdTitle(code))
        val dialog = alertDialog.create()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        val adapter = CustomSpinnerAdapter(data as List<ResponseCountries>)
        val layoutManager = LinearLayoutManager(context)
        mBinding?.rvItems?.layoutManager = layoutManager
        mBinding?.rvItems?.adapter = adapter
        adapter.customActionsSpinner = object : CustomSpinnerAdapter.CustomActionSpinner {
            override fun onItemSelected(position: Int) {
                customActionSpinner.onItemSelected(position)
                dismiss()
            }
        }
        return dialog
    }

}
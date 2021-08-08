package com.jhonnatan.kalunga.presentation.core.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import com.jhonnatan.kalunga.data.typeDocument.entities.ResponseDocumentType
import com.jhonnatan.kalunga.domain.models.enumeration.CodeTypeSpinner
import com.jhonnatan.kalunga.domain.models.utils.UtilsCountry

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.session.adapters
 * Created by Jhonnatan E. Zamudio P. on 24/07/2021 at 12:54 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class CustomSpinnerAdapter(private val data: List<Any>, private val code: Int) :
    RecyclerView.Adapter<CustomSpinnerAdapter.CustomViewHolder>() {

    var customActionsSpinner: CustomActionSpinner? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.spinner_items, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data = data[position]
        createStyleDialog(position, holder)
        setDataSpinner(data, holder)
        holder.container.setOnClickListener {
            customActionsSpinner?.onItemSelected(position)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDataSpinner(data: Any, holder: CustomViewHolder) {
        when (code) {
            CodeTypeSpinner.COUNTRIES.code -> {
                data as ResponseCountries
                holder.description.text = data.codPais + " " + data.pais
                holder.icon.setImageResource(UtilsCountry().getIdFlag(data.pais))
            }
            CodeTypeSpinner.TYPE_DOCUMENT.code -> {
                data as ResponseDocumentType
                holder.description.text = data.abbreviate + " - " + data.description
            }
        }
    }

    private fun createStyleDialog(position: Int, holder: CustomViewHolder) {
        if (position % 2 == 0) {
            holder.container.setBackgroundResource(R.drawable.background_item_1)
            holder.description.setTextColor(-0x1)
        } else
            holder.container.setBackgroundResource(R.drawable.background_item_2)
    }

    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.imageViewIcon)
        val description: TextView = view.findViewById(R.id.textViewDescription)
        val container: LinearLayout = view.findViewById(R.id.linearLayoutContainer)
    }

    interface CustomActionSpinner {
        fun onItemSelected(position: Int)
    }

}

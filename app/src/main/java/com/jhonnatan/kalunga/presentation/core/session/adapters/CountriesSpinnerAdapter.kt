package com.jhonnatan.kalunga.presentation.core.session.adapters

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.R.color.colorAccent
import com.jhonnatan.kalunga.data.cities.entities.ResponseCountries
import io.github.serpro69.kfaker.provider.Color

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.presentation.core.session.adapters
 * Created by Jhonnatan E. Zamudio P. on 24/07/2021 at 12:54 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class CountriesSpinnerAdapter (private val data: List<ResponseCountries>) : RecyclerView.Adapter<CountriesSpinnerAdapter.CustomViewHolder>() {

    var customActionsSpinner: CustomActionSpinner? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.spinner_items, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data = data[position]
        var flag = 0
        if (position % 2 == 0){
            holder.container.setBackgroundResource(R.drawable.background_item_1)
            holder.description.setTextColor(-0x1)
        } else{
            holder.container.setBackgroundResource(R.drawable.background_item_2)
        }
        holder.description.text = data.codPais + " " + data.pais
        holder.container.setOnClickListener {
            customActionsSpinner?.onItemSelected(position)
        }
        when(data.pais){
            "Colombia" -> flag = R.mipmap.colombia
            "Venezuela" -> flag = R.mipmap.venezuela
            "España" -> flag = R.mipmap.espana
            "Italia" -> flag = R.mipmap.italia
            "Estados Unidos" -> flag = R.mipmap.estados_unidos
            "Chile" -> flag = R.mipmap.chile
            "Perú" -> flag = R.mipmap.peru
        }
        holder.icon.setImageResource(flag)
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

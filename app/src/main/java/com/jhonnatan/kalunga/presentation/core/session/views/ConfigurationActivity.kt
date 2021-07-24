package com.jhonnatan.kalunga.presentation.core.session.views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityConfigurationBinding
import com.jhonnatan.kalunga.presentation.core.session.viewModels.ConfigurationViewModel
import com.jhonnatan.kalunga.presentation.core.session.viewModels.ConfigurationViewModelFactory
import com.jhonnatan.kalunga.presentation.core.utils.LoadingDialog
import kotlinx.coroutines.DelicateCoroutinesApi


class ConfigurationActivity : AppCompatActivity() {
    private lateinit var viewModel: ConfigurationViewModel
    private lateinit var binding: ActivityConfigurationBinding

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ConfigurationViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ConfigurationViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_configuration)
        binding.lifecycleOwner = this
        binding.vModel = viewModel

        binding.imageViewBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.numberFormat.observe(this, {
            binding.editTextPhone.setText(it)
        })
        val adapterCountries = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, viewModel.countriesSpinnerArray
        )

        val adapterTypeDocument = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, viewModel.typeDocumentsSpinnerArray
        )

        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCountry.adapter = adapterCountries

        adapterTypeDocument.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDocumentType.adapter = adapterTypeDocument

        fun onBackPressed() {
            val intent = Intent(
                this@ConfigurationActivity,
                SignUpActivity::class.java
            )
            startActivity(intent)
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
            finish()
        }
    }
}
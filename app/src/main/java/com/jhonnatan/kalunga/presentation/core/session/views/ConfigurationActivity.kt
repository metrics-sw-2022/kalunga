package com.jhonnatan.kalunga.presentation.core.session.views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityConfigurationBinding
import com.jhonnatan.kalunga.presentation.core.session.adapters.CountriesSpinnerAdapter
import com.jhonnatan.kalunga.presentation.core.session.viewModels.ConfigurationViewModel
import com.jhonnatan.kalunga.presentation.core.session.viewModels.ConfigurationViewModelFactory
import com.jhonnatan.kalunga.presentation.core.utils.ListDialog
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class ConfigurationActivity : AppCompatActivity() {
    private lateinit var viewModel: ConfigurationViewModel
    private lateinit var binding: ActivityConfigurationBinding


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

        binding.spinnerCountry.setOnClickListener {
            val dialog = ListDialog(
                viewModel.countriesList,
                object : CountriesSpinnerAdapter.CustomActionSpinner {
                    override fun onItemSelected(position: Int) {
                        viewModel.countrySelectedPosition.value = position
                    }
                })
            dialog.show(this.supportFragmentManager, resources.getString(R.string.país))
        }

/*
        binding.spnObservation.setOnClickListener {
            val dialog = ListObservationDialog(observations, object : ObservationsSpinnerAdapter.ObservationActionSpinner {
                override fun onObservationSelected(position: Int) {
                    viewModel.observacionSelectedPosition.value = position
                    if (viewModel.requirePhoto(position)) {
                        capturePhoto()
                    }
                }
            })
            dialog.show(requireActivity().supportFragmentManager, "Selecciona observación")
        }

        viewModel.observacionSelectedPosition.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                viewModel.validate()
                binding.spnObservation.text = requireContext().getString(R.string.observation_description_spinner, observations[it].codObservacion, observations[it].descripcion)
            }
        })*/

        viewModel.numberFormat.observe(this, {
            binding.editTextPhone.setText(it)
        })

    }

    override fun onBackPressed() {
        val intent = Intent(
            this@ConfigurationActivity,
            SignUpActivity::class.java
        )
        startActivity(intent)
        overridePendingTransition(R.anim.right_in, R.anim.right_out)
        finish()
    }
}


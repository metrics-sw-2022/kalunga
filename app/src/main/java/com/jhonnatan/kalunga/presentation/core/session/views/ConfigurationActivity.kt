package com.jhonnatan.kalunga.presentation.core.session.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityConfigurationBinding
import com.jhonnatan.kalunga.domain.models.utils.UtilsCountry
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

        viewModel.countrySelectedPosition.observe(this, {
            if (it != null) {
                binding.spinnerCountry.setCompoundDrawablesWithIntrinsicBounds(
                    UtilsCountry().getIdFlag(viewModel.countriesList[it].pais),
                    0,
                    R.drawable.ic_arrow_drop_down,
                    0
                )
            }
        })

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


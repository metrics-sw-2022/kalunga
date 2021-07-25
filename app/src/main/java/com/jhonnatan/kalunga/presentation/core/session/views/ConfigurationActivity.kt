package com.jhonnatan.kalunga.presentation.core.session.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityConfigurationBinding
import com.jhonnatan.kalunga.domain.models.enumeration.CodeTypeSpinner
import com.jhonnatan.kalunga.domain.models.utils.UtilsCountry
import com.jhonnatan.kalunga.presentation.core.session.viewModels.ConfigurationViewModel
import com.jhonnatan.kalunga.presentation.core.session.viewModels.ConfigurationViewModelFactory
import com.jhonnatan.kalunga.presentation.core.utils.CustomSpinnerAdapter
import com.jhonnatan.kalunga.presentation.core.utils.ListDialog
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class ConfigurationActivity : AppCompatActivity() {
    private lateinit var viewModel: ConfigurationViewModel
    private lateinit var binding: ActivityConfigurationBinding
    private val tag = "Configuration"

    @SuppressLint("SetTextI18n")
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

        binding.buttonCountry.setOnClickListener {
            createDialogSpinner(viewModel.countriesList, CodeTypeSpinner.COUNTRIES.code)
        }

        binding.buttonDocumentType.setOnClickListener {
            createDialogSpinner(viewModel.typeDocumentsList, CodeTypeSpinner.TYPE_DOCUMENT.code)
        }

        binding.buttonDocumentType.setSelected(true)

        viewModel.countrySelectedPosition.observe(this, {
            if (it != null) {
                binding.buttonCountry.setCompoundDrawablesWithIntrinsicBounds(
                    UtilsCountry().getIdFlag(viewModel.countriesList[it].pais),
                    0,
                    R.drawable.ic_arrow_drop_down,
                    0
                )
            }
        })



        viewModel.typeDocumentSelectedPosition.observe(this, {
            if (it != null) {
                binding.buttonDocumentType.text =
                    viewModel.typeDocumentsList[it].abbreviate + getString(R.string.separador) + viewModel.typeDocumentsList[it].description
            }
        })

        viewModel.numberFormat.observe(this, {
            binding.editTextPhone.setText(it)
        })

    }

    private fun createDialogSpinner(dataList: List<Any>, code: Int) {
        val dialog = ListDialog(
            dataList,
            code,
            object : CustomSpinnerAdapter.CustomActionSpinner {
                override fun onItemSelected(position: Int) {
                    when (code) {
                        CodeTypeSpinner.COUNTRIES.code -> viewModel.countrySelectedPosition.value =
                            position
                        CodeTypeSpinner.TYPE_DOCUMENT.code -> viewModel.typeDocumentSelectedPosition.value =
                            position
                    }
                }
            })
        dialog.show(this.supportFragmentManager, tag)
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


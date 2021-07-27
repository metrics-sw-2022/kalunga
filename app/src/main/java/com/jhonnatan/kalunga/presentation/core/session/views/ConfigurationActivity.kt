package com.jhonnatan.kalunga.presentation.core.session.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityConfigurationBinding
import com.jhonnatan.kalunga.domain.models.enumeration.CodeTypeSpinner
import com.jhonnatan.kalunga.domain.models.utils.UtilsCountry
import com.jhonnatan.kalunga.presentation.core.legal.views.TermsAndPrivacyActivity
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
            viewModel.getDataCitiesByCodeCountry()
            binding.textViewCountryCode.text = viewModel.countriesList[it].codPais
            viewModel.formatPhone(binding.editTextPhone.text.toString() , '1')
        })

        viewModel.typeDocumentSelectedPosition.observe(this, {
            if (it != null) {
                binding.buttonDocumentType.text =
                    viewModel.typeDocumentsList[it].abbreviate + getString(R.string.separador) + viewModel.typeDocumentsList[it].description
            }
        })

        viewModel.numberFormat.observe(this, {
            binding.editTextPhone.setText(it)
            binding.editTextPhone.setSelection(binding.editTextPhone.length())
        })

        viewModel.citiesList.observe(this, {
            citiesList -> binding.textViewCity.setAdapter(ArrayAdapter(this@ConfigurationActivity, R.layout.support_simple_spinner_dropdown_item,citiesList[0].data))
        })


        binding.textViewTerms.makeLinks(
            Pair(getString(R.string.condiciones_de_uso), View.OnClickListener {
                goToTermsAndPrivacy(getString(R.string.terms_of_use))
            }),
            Pair(getString(R.string.declaracion_de_privacidad), View.OnClickListener {
                goToTermsAndPrivacy(getString(R.string.privacy_statement))
            }))

        viewModel.emailValue.value = intent.getStringExtra("ACCOUNT")
        viewModel.nameValue.value = intent.getStringExtra("FULL_NAME")
        viewModel.passwordValue.value = intent.getStringExtra("PASSWORD_USER")
        viewModel.setInitialValues()


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

    private fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
        val spannableString = SpannableString(this.text)
        var startIndexOfLink = -1
        for (link in links) {
            val clickableSpan = object : ClickableSpan() {
                @SuppressLint("ResourceAsColor")
                override fun updateDrawState(textPaint: TextPaint) {
                    textPaint.color = R.color.purple
                    textPaint.isUnderlineText = true
                }

                override fun onClick(view: View) {
                    Selection.setSelection((view as TextView).text as Spannable, 0)
                    view.invalidate()
                    link.second.onClick(view)
                }
            }
            startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
            spannableString.setSpan(
                clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        this.movementMethod =
            LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
        this.setText(spannableString, TextView.BufferType.SPANNABLE)
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

    private fun goToTermsAndPrivacy(type: String) {
        val intent = Intent(this@ConfigurationActivity, TermsAndPrivacyActivity::class.java)
        intent.putExtra("TYPE", type)
        startActivity(intent)
        overridePendingTransition(R.anim.up_in, R.anim.up_out)
        finish()
    }
}


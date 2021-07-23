package com.jhonnatan.kalunga.presentation.core.session.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityConfigurationBinding
import com.jhonnatan.kalunga.presentation.core.session.viewModels.ConfigurationViewModel
import com.jhonnatan.kalunga.presentation.core.session.viewModels.ConfigurationViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi


class ConfigurationActivity : AppCompatActivity() {
    private lateinit var viewModel: ConfigurationViewModel
    private lateinit var binding: ActivityConfigurationBinding

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ConfigurationViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, viewModelFactory)[ConfigurationViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_configuration)
        binding.lifecycleOwner = this
        binding.vModel = viewModel

        val spinnerArray: MutableList<String> = ArrayList()
        spinnerArray.add("item1")
        spinnerArray.add("item2")

        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, spinnerArray
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val sItems = binding.spinnerDocumentType
        sItems.adapter = adapter


        binding.imageViewBack.setOnClickListener {
            onBackPressed()
        }

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
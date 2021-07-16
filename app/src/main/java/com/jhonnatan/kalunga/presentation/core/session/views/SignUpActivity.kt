package com.jhonnatan.kalunga.presentation.core.session.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivitySignUpBinding
import com.jhonnatan.kalunga.domain.models.enumeration.CodeField
import com.jhonnatan.kalunga.presentation.core.session.viewModels.SignUpViewModel
import com.jhonnatan.kalunga.presentation.core.session.viewModels.SignUpViewModelFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: ActivitySignUpBinding
    private val TAG = "SignUp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = SignUpViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        binding.vModel = viewModel


        binding.editTextEmail.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.areFieldsEmpty(s,CodeField.EMAIL_FIELD.code)
            }

        })

        binding.editTextName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.areFieldsEmpty(s,CodeField.NAME_FIELD.code)
            }

        })

        binding.editTextPassword.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.areFieldsEmpty(s,CodeField.PASSWORD_FIELD.code)
            }

        })

        binding.editTextPasswordConfirm.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.areFieldsEmpty(s,CodeField.PASSWORD_CONFIRM_FIELD.code)
            }

        })

        viewModel.errorEmail.observe(this, {
            binding.textViewEmailError.text = it
        })

        viewModel.errorName.observe(this, {
            binding.textViewNameError.text = it
        })

        viewModel.errorPassword.observe(this, {
            binding.textViewPasswordError.text = it
        })

        viewModel.errorPasswordConfirm.observe(this, {
            binding.textViewPasswordConfirmError.text = it
        })
    }
}
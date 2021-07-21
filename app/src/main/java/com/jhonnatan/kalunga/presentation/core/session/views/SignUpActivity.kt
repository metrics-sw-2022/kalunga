package com.jhonnatan.kalunga.presentation.core.session.views

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivitySignUpBinding
import com.jhonnatan.kalunga.domain.models.enumeration.CodeUserData
import com.jhonnatan.kalunga.presentation.core.home.views.StartingScreenActivity
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

        binding.imageViewBack.setOnClickListener {
            onBackPressed()
        }

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

        viewModel.buttonContinueDrawable.observe(this, {
            binding.buttonContinue.setBackgroundResource(it)
        })

        viewModel.buttonContinueEnable.observe(this, {
            binding.buttonContinue.isEnabled = it
        })

        viewModel.showPassword.observe(this, {
            if (it){
                binding.editTextPassword.transformationMethod =
                    PasswordTransformationMethod()
                binding.imageViewShow.setBackgroundResource(R.drawable.ic_eye_line)
            } else {
                binding.editTextPassword.transformationMethod = null
                binding.imageViewShow.setBackgroundResource(R.drawable.ic_eye)
            }
            binding.editTextPassword.setSelection(binding.editTextPassword.length())
        })

        viewModel.showPasswordConfirm.observe(this, {
            if (it){
                binding.editTextPasswordConfirm.transformationMethod =
                    PasswordTransformationMethod()
                binding.imageViewShowConfirm.setBackgroundResource(R.drawable.ic_eye_line)
            } else {
                binding.editTextPasswordConfirm.transformationMethod = null
                binding.imageViewShowConfirm.setBackgroundResource(R.drawable.ic_eye)
            }
            binding.editTextPasswordConfirm.setSelection(binding.editTextPasswordConfirm.length())
        })

        viewModel.navigateToConfiguration.observe(this, {
            if (it == true)
                goToConfiguration()
        })

        viewModel.navigateToLogIn.observe(this, {
            if (it == true)
                goToLogIn()
        })
    }

    private fun goToConfiguration() {
        val intent = Intent(this@SignUpActivity, ConfigurationActivity::class.java)
        intent.putExtra("ACCOUNT", binding.editTextEmail.text.toString())
        intent.putExtra("PASSWORD_USER", binding.editTextPassword.text.toString())
        intent.putExtra("STATUS_USER", CodeUserData.UNVALIDATED_USER.value)
        intent.putExtra("SESSION_STATE", CodeUserData.FINISHED.value)
        intent.putExtra("TYPE_USER", CodeUserData.STANDART.value)
        intent.putExtra("EMAIL", binding.editTextEmail.text.toString())
        intent.putExtra("FULL_NAME", binding.editTextName.text.toString())
        startActivity(intent)
        overridePendingTransition(R.anim.left_in, R.anim.left_out)
        finish()
    }

    private fun goToLogIn() {
        val intent = Intent(this@SignUpActivity, LogInActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        finish()
    }

    override fun onBackPressed() {
        val intent = Intent(this@SignUpActivity, StartingScreenActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.right_in, R.anim.right_out)
        finish()
    }
}
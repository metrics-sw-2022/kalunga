package com.jhonnatan.kalunga.presentation.core.home.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityStartingScreenBinding
import com.jhonnatan.kalunga.domain.models.CodeActivityForResult
import com.jhonnatan.kalunga.domain.models.TypeSnackBar
import com.jhonnatan.kalunga.presentation.core.home.viewModels.StartingScreenViewModel
import com.jhonnatan.kalunga.presentation.core.home.viewModels.StartingScreenViewModelFactory
import com.jhonnatan.kalunga.presentation.core.session.views.SignUpActivity
import com.jhonnatan.kalunga.presentation.core.utils.CustomSnackBar
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class StartingScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: StartingScreenViewModel
    private lateinit var binding: ActivityStartingScreenBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private val TAG = "StartingScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = StartingScreenViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[StartingScreenViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_starting_screen)
        binding.lifecycleOwner = this
        binding.vModel = viewModel
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)

        viewModel.navigateToSignUp.observe(this,{
            if (it == true)
                goToSignUp()
        })

        viewModel.loginGoogle.observe(this,{
            if (it == true)
                viewModel.checkOnline(this)
        })

        viewModel.isConected.observe(this,{
            if (it == true){
                val loginIntent: Intent = mGoogleSignInClient.signInIntent
                startActivityForResult(loginIntent, CodeActivityForResult.LOGIN_GOOGLE.code)
            } else
                viewModel.snackBarTextError.postValue(getString(R.string.debe_tener_internet_continuar_google))
        })

        viewModel.snackBarTextError.observe(this,{
            CustomSnackBar().showSnackBar(
                it,
                binding.layoutContain,
                TypeSnackBar.WARNING.code,
                this
            )
        })
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CodeActivityForResult.LOGIN_GOOGLE.code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            if (task.isSuccessful) {
                val acct: GoogleSignInAccount = task.result!!
                println("El valor es " + acct.email)
                println("El valor es " + acct.id)
                println("El valor es " + acct.displayName)
            } else {
                Log.e(TAG, "loginGoogleError:" + task.exception.toString())
                viewModel.snackBarTextError.postValue(getString(R.string.error_login_google))
            }
            mGoogleSignInClient.signOut()
        }
    }

    private fun goToSignUp() {
        val intent = Intent(this@StartingScreenActivity, SignUpActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_in, R.anim.left_out)
        finish()
    }
}
package com.jhonnatan.kalunga.presentation.core.home.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.jhonnatan.kalunga.R
import com.jhonnatan.kalunga.databinding.ActivityStartingScreenBinding
import com.jhonnatan.kalunga.domain.models.entities.UserAccountData
import com.jhonnatan.kalunga.domain.models.enumeration.*
import com.jhonnatan.kalunga.presentation.core.home.viewModels.StartingScreenViewModel
import com.jhonnatan.kalunga.presentation.core.home.viewModels.StartingScreenViewModelFactory
import com.jhonnatan.kalunga.presentation.core.session.views.ConfigurationActivity
import com.jhonnatan.kalunga.presentation.core.session.views.LogInActivity
import com.jhonnatan.kalunga.presentation.core.session.views.SignUpActivity
import com.jhonnatan.kalunga.presentation.core.utils.CustomSnackBar
import com.jhonnatan.kalunga.presentation.core.utils.LoadingDialog
import com.jhonnatan.kalunga.presentation.features.dashboard.views.DashboardActivity
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class StartingScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: StartingScreenViewModel
    private lateinit var binding: ActivityStartingScreenBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private val callbackManager = CallbackManager.Factory.create()
    private val TAG = "StartingScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = StartingScreenViewModelFactory.getInstance(this)
        val loadingDialog = LoadingDialog(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[StartingScreenViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_starting_screen)
        binding.lifecycleOwner = this
        binding.vModel = viewModel
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)

        binding.buttonRegistrarse.setOnClickListener { goToSignUp() }

        binding.buttonGoogle.setOnClickListener {
            viewModel.loadingDialog.value = true
            if (viewModel.checkOnline(this))
                loginWithGoogle()
            else {
                viewModel.loadingDialog.value = false
                viewModel.snackBarTextWarning.postValue(getString(R.string.debe_tener_internet_continuar_google))
            }
        }

        binding.buttonFacebook.setOnClickListener {
            viewModel.loadingDialog.value = true
            if (viewModel.checkOnline(this))
                loginWithFacebook()
            else {
                viewModel.loadingDialog.value = false
                viewModel.snackBarTextWarning.postValue(getString(R.string.internet_sesion_facebook))
            }
        }

        binding.textViewIniciarSesion.setOnClickListener { goToLogIn() }

        viewModel.snackBarTextWarning.observe(this, {
            CustomSnackBar().showSnackBar(
                it,
                binding.layoutContain,
                TypeSnackBar.WARNING.code,
                this
            )
        })

        viewModel.snackBarTextError.observe(this, {
            CustomSnackBar().showSnackBar(
                it,
                binding.layoutContain,
                TypeSnackBar.ERROR.code,
                this
            )
        })

        viewModel.loadingDialog.observe(this, {
            if (it == true)
                loadingDialog.startLoadingDialog()
            else
                loadingDialog.hideLoadingDialog()
        })

        viewModel.navigateToConfiguration.observe(this, { goToConfiguration() })

        viewModel.navigateToDashboard.observe(this, { gotoDashboard() })
    }

    private fun goToLogIn() {
        val intent = Intent(this@StartingScreenActivity, LogInActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_in, R.anim.left_out)
        finish()
    }

    private fun loginWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    result?.let {
                        val token = it.accessToken
                        val request = GraphRequest.newMeRequest(token) { account, response ->
                            viewModel.userAccount.value = UserAccountData(
                                account.getString("id"),
                                account.getString("name"),
                                account.getString("email")
                            )
                            viewModel.serverUserExist()
                        }
                        val parameters = Bundle()
                        parameters.putString("fields", "id,name,email")
                        request.parameters = parameters
                        request.executeAsync()
                        LoginManager.getInstance().logOut()
                    }
                }

                override fun onCancel() {
                    viewModel.loadingDialog.value = false
                }

                override fun onError(error: FacebookException?) {
                    Log.e(TAG, "loginFacebookError:" + error)
                    viewModel.loadingDialog.value = false
                    viewModel.snackBarTextError.postValue(getString(R.string.error_login_facebook))
                }
            })
    }

    private fun loginWithGoogle() {
        val loginIntent: Intent = mGoogleSignInClient.signInIntent
        @Suppress("DEPRECATION")
        startActivityForResult(loginIntent, CodeActivityForResult.LOGIN_GOOGLE.code)
    }

    private fun gotoDashboard() {
        val intent = Intent(this@StartingScreenActivity, DashboardActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_in, R.anim.left_out)
        finish()
    }

    private fun goToConfiguration() {
        val intent = Intent(this@StartingScreenActivity, ConfigurationActivity::class.java)
        intent.putExtra("ACCOUNT", viewModel.userAccount.value!!.id)
        intent.putExtra("PASSWORD_USER", viewModel.userAccount.value!!.id)
        intent.putExtra("STATUS_USER", CodeStatusUser.ENABLED_USER.code)
        intent.putExtra("SESSION_STATE", CodeSessionState.STARTED.code)
        intent.putExtra("TYPE_USER", CodeTypeUser.STANDART.code)
        intent.putExtra("EMAIL", viewModel.userAccount.value!!.email)
        intent.putExtra("FULL_NAME", viewModel.userAccount.value!!.name)
        startActivity(intent)
        overridePendingTransition(R.anim.left_in, R.anim.left_out)
        finish()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CodeActivityForResult.LOGIN_GOOGLE.code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            val errorCode = task.exception?.message
            if (task.isSuccessful) {
                val acct: GoogleSignInAccount = task.result!!
                viewModel.userAccount.value =
                    UserAccountData(acct.id!!, acct.displayName!!, acct.email!!)
                viewModel.serverUserExist()
            } else if (errorCode!!.contains("12501"))
                viewModel.loadingDialog.value = false
            else {
                Log.e(TAG, "loginGoogleError:" + task.exception.toString())
                viewModel.loadingDialog.value = false
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
package org.android.go.sopt.presentation.login

import android.app.Activity
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.data.model.AuthState
import org.android.go.sopt.domain.model.User
import org.android.go.sopt.data.model.remote.request.RequestSignInDto
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.MainActivity
import org.android.go.sopt.presentation.signup.SignupActivity
import org.android.go.sopt.util.PrefUtilObject
import org.android.go.sopt.util.PrefUtilObject.getID
import org.android.go.sopt.util.PrefUtilObject.getPW
import org.android.go.sopt.util.extension.getParcelized
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.showToast

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var signupResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.vm = viewModel
        setContentView(binding.root)
        initLayout()
        checkSignInSuccess()
        isSavedCheck()
        setSignupBtnEvent()
        setLoginBtnEvent()
    }

    private fun initLayout() {
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun setLoginBtnEvent() {
        binding.loginBtn.setOnClickListener {
            completeSignIn()
        }
    }

    private fun completeSignIn() {
        val userData = viewModel.getUser()
        viewModel.signIn(
            RequestSignInDto(
                id = userData.id,
                password = userData.pw
            )
        )
    }

    private fun checkSignInSuccess() {
        viewModel.signinState.observe(this) { signinState ->
            if (signinState == AuthState.SUCCESS) {
                goToMain()
            } else {
                showToast(getString(R.string.join_fail_string))
            }
        }
    }

    private fun isSavedCheck() {
        if (getID(this).isNotBlank() && getPW(this).isNotBlank()) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun goToMain() {
        val userData = viewModel.getUser()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_USER, userData)
        startActivity(intent)
        showToast(getString(R.string.login_success_string))
        PrefUtilObject.setID(this, userData.id)
        PrefUtilObject.setPW(this, userData.pw)
    }

    private fun setSignupBtnEvent() {
        var userData : User
        signupResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    userData = result.data?.getParcelized(EXTRA_USER)!!
                    binding.etId.setText(userData.id)
                    binding.etPw.setText(userData.pw)
                }
            }
        binding.joinBtn.setOnClickListener {
            val joinIntent = Intent(this, SignupActivity::class.java)
            signupResultLauncher.launch(joinIntent)
        }
    }

}
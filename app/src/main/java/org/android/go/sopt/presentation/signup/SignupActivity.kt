package org.android.go.sopt.presentation.signup

import android.app.Activity
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.data.model.AuthState
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.showToast

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        binding.vm = viewModel
        setContentView(binding.root)
        setEditTextValidations()
        checkIsValidandFilled()
        setSignUpBtnCheckEvent()
        checkSignUpSuccess()
    }


    private fun setEditTextValidations() {
        with(binding){
            signupEtId.doOnTextChanged { text, _, _, _ ->
                viewModel.validateId(text.toString())
            }
            signupEtPw.doOnTextChanged { text, _, _, _ ->
                viewModel.validatePassword(text.toString())
            }
            signupEtName.doOnTextChanged { text, _, _, _ ->
                viewModel.validateName(text.toString())
            }
            signupEtSpecial.doOnTextChanged { text, _, _, _ ->
                viewModel.validateSpeciality(text.toString())
            }
        }
    }

    private fun checkIsValidandFilled() {
        viewModel.isValidAndFilled.observe(this) { isValidAndFilled ->
            binding.signupBtn.isEnabled = isValidAndFilled
        }
    }

    private fun setSignUpBtnCheckEvent() {
        with(binding) {
            signupBtn.setOnClickListener {
                completeSignUp()
            }
        }
    }


    private fun completeSignUp() {
        val userData = viewModel.getUser()
        viewModel.signUp(
            RequestSignUpDto(
                id = userData.id,
                password = userData.pw,
                name = userData.name,
                skill = userData.special
            )
        )
        goToLogin()
    }

    private fun checkSignUpSuccess(){
        viewModel.signupState.observe(this) { signupState ->
            if(signupState == AuthState.SUCCESS){
                goToLogin()
            }else{
                showToast(getString(R.string.join_fail_string))
            }
        }
    }

    fun goToLogin() {
        Intent(this, LoginActivity::class.java).apply {
            this.putExtra(EXTRA_USER, viewModel.getUser())
            setResult(Activity.RESULT_OK, this)
            if (!isFinishing) finish()
        }
    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}


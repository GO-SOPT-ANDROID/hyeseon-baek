package org.android.go.sopt.presentation.signup

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.util.extension.hideKeyboard

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        binding.vm = viewModel
        setContentView(binding.root)
        initLayout()
        checkIsValidandFilled()
        setSignUpBtnCheckEvent()
    }

    private fun initLayout(){
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun checkIsValidandFilled() {
        viewModel.getIsValidAndFilled().observe(this) { _isValidAndFilled ->
            binding.signupBtn.isEnabled = _isValidAndFilled
            Log.e("over",_isValidAndFilled.toString())
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
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}


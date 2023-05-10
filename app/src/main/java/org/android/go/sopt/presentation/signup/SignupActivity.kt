package org.android.go.sopt.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.data.ApiFactory
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.extension.showToast
import retrofit2.Call
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel by viewModels<SignupViewModel>()
    private val singUpService = ApiFactory.ServicePool.signUpService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        binding.vm = viewModel
        setContentView(binding.root)
        setSignUpBtnCheckEvent()
        initLayout()
    }

    private fun initLayout(){
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun setSignUpBtnCheckEvent() {
        binding.signupBtn.setOnClickListener {
            checkIsValid()
        }
    }


    private fun checkIsValid() {
        with(binding) {
            if (viewModel.isValid(id = signupEtId.text.toString(), pw = signupEtPw.text.toString())) {
                completeSignUp()
            }else{
                showSnackbar(binding.root,getString(R.string.join_condition_fail_string))
            }
        }
    }

    private fun completeSignUp() {
        val userData = viewModel.getUser()
        singUpService.login(
            RequestSignUpDto(
                id = userData.id,
                password = userData.pw,
                name = userData.name,
                skill = userData.special
            )
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let {
                        showToast(it)
                        showSnackbar(binding.root,getString(R.string.join_success_string))
                        Intent(this@SignupActivity, LoginActivity::class.java).apply {
                            this.putExtra(Intent.EXTRA_USER, userData)
                            setResult(RESULT_OK, this)
                            if (!isFinishing) finish()
                        }
                    }
                } else {
                    response.body()?.message?.let {
                        Log.e("log",it)
                        showSnackbar(binding.root,getString(R.string.join_fail_string))
                    }
                }
            }
            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                t.message?.let { showToast(it)
                    Log.e("log",it)}

            }

        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}


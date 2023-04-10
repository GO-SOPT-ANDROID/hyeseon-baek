package org.android.go.sopt.presentation.join

import android.content.Intent
import android.opengl.ETC1.isValid
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityJoinBinding
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.presentation.login.LoginViewModel

class JoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinBinding
    private val viewModel by viewModels<JoinViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setJoinbtnEvent()
    }

    private fun setJoinbtnEvent() {
        binding.joinBtnSignup.setOnClickListener {
            isCheck()
        }
    }


    private fun isCheck() {
        with(binding) {
            if (viewModel.isValid(joinEtID.text.toString(), joinEtPW.text.toString())) {
                //성공시 회원 정보 data class로 넣어줌
                val userData = User(
                    binding.joinEtID.text.toString(),
                    binding.joinEtPW.text.toString(),
                    binding.joinEtName.text.toString(),
                    binding.joinEtSpecial.text.toString()
                )
                Intent(this@JoinActivity, LoginActivity::class.java).apply {
                    putExtra("USER", userData)
                    setResult(RESULT_OK, this)
                }
                finish()
                makeSnackBar(getString(R.string.join_success_string))
                return
            }
                makeSnackBar(getString(R.string.join_fail_string))
        }
    }

    private fun makeSnackBar(string: String) {
        Snackbar.make(
            binding.root, string, Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

}
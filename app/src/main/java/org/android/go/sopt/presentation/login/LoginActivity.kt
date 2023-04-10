package org.android.go.sopt.presentation.login

import android.app.Activity
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.R
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.MainActivity
import org.android.go.sopt.presentation.join.JoinActivity
import org.android.go.sopt.utiil.UtilObject
import org.android.go.sopt.utiil.extension.getParcelized
import org.android.go.sopt.utiil.extension.showToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var signupResultLauncher : ActivityResultLauncher<Intent>
    private var userData : User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        goMain()
        setContentView(binding.root)
        setSignupbtnEvent()
        setLoginbtnEvent()
    }

    private fun goMain(){
        if(isSavedCheck()){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun setLoginbtnEvent(){
        binding.loginBtn.setOnClickListener {
            if(isLoginCheck()){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(EXTRA_USER, userData)
                startActivity(intent)
                showToast(getString(R.string.login_success_string))
                UtilObject.setID(this,userData?.id.toString())
                UtilObject.setPW(this,userData?.pw.toString())
            }
        }
    }

    private fun isLoginCheck(): Boolean{
        return userData?.id  == binding.etId.text.toString() && userData?.pw == binding.etPw.text.toString()
    }

    private fun isSavedCheck():Boolean{
        return UtilObject.getID(this).isNotBlank() && UtilObject.getPW(this).isNotBlank()
    }

    private fun setSignupbtnEvent() {
        signupResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK){
                    userData = result.data?.getParcelized(EXTRA_USER)
                    binding.etId.setText(userData?.id)
                    binding.etPw.setText(userData?.pw)
                }
            }
        binding.joinBtn.setOnClickListener {
            val joinIntent = Intent(this,JoinActivity::class.java)
            signupResultLauncher.launch(joinIntent)
        }
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}
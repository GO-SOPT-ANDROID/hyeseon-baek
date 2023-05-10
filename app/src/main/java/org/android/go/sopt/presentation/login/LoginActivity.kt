package org.android.go.sopt.presentation.login

import android.app.Activity
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import androidx.activity.viewModels
import org.android.go.sopt.data.model.main.User
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.MainActivity
import org.android.go.sopt.presentation.signup.SignupActivity
import org.android.go.sopt.util.PrefUtilObject
import org.android.go.sopt.util.PrefUtilObject.getID
import org.android.go.sopt.util.PrefUtilObject.getPW
import org.android.go.sopt.util.extension.getParcelized
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.showToast

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
        initLayout()
    }

    private fun initLayout(){
        binding.root.setOnClickListener {
            hideKeyboard()
        }
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
                PrefUtilObject.setID(this,userData?.id.toString())
                PrefUtilObject.setPW(this,userData?.pw.toString())
            }
        }
    }

    private fun isLoginCheck(): Boolean{
        return userData?.id  == binding.etId.text.toString() && userData?.pw == binding.etPw.text.toString()
    }

    private fun isSavedCheck():Boolean{
        return getID(this).isNotBlank() && getPW(this).isNotBlank()
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
            val joinIntent = Intent(this,SignupActivity::class.java)
            signupResultLauncher.launch(joinIntent)
        }
    }

}
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
                intent.putExtra("USER", userData)
                startActivity(intent)
                Toast.makeText(this@LoginActivity, getString(R.string.login_success_string), Toast.LENGTH_SHORT).show()
                UtilObject.setID(this,userData?.id.toString())
                UtilObject.setPW(this,userData?.pw.toString())
            }
        }
    }

    private fun isLoginCheck(): Boolean{
        return userData?.id  == binding.etID.text.toString() && userData?.pw == binding.etPW.text.toString()
    }

    private fun isSavedCheck():Boolean{
        return UtilObject.getID(this).isNotBlank() && UtilObject.getPW(this).isNotBlank()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setSignupbtnEvent() {
        signupResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK){
                    userData = result.data?.getParcelableExtra("USER",User::class.java)
                    binding.etID.setText(userData?.id)
                    binding.etPW.setText(userData?.pw)
                }
            }
        binding.joinBtn.setOnClickListener {
            val joinIntent = Intent(this,JoinActivity::class.java)
            signupResultLauncher.launch(joinIntent)
        }
    }

    private fun makeSnackBar(string: String){
        Snackbar.make(
            binding.root, string , Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }


}
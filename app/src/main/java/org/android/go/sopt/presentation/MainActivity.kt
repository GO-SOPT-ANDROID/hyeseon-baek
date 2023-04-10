package org.android.go.sopt.presentation

import android.annotation.SuppressLint
import android.content.Intent.EXTRA_USER
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.util.UtilObject.getID

class MainActivity : AppCompatActivity() {
    private var userData : User? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putData()
        getSaved()
    }

    @SuppressLint("SetTextI18n")
    private fun putData(){
        userData = intent.getParcelableExtra(EXTRA_USER, User::class.java)
        binding.mainTvName.append(getString(R.string.main_tv_name) + userData?.name)
        binding.mainTvSpecial.append(getString(R.string.main_tv_special) + userData?.special)
    }

    private fun getSaved(){
        //자동로그인으로 접근한경우
            binding.mainTvName.text = getID(this)
            binding.mainTvSpecial.text = getString(R.string.main_alert)
    }
}
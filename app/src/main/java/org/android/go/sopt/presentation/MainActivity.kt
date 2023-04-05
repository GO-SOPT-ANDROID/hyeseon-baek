package org.android.go.sopt.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityJoinBinding
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.presentation.join.JoinViewModel

class MainActivity : AppCompatActivity() {
    private var userData : User? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putData()
    }

    @SuppressLint("SetTextI18n")
    private fun putData(){
        userData = intent.getParcelableExtra("USER", User::class.java)
        binding.mainTvName.text = getString(R.string.main_tv_name) + userData?.name
        binding.mainTvSpecial.text = getString(R.string.main_tv_special) + userData?.special
    }
}
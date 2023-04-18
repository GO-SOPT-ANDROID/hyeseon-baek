package org.android.go.sopt.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.viewModels
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityJoinBinding
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.presentation.home.HomeFragment
import org.android.go.sopt.presentation.home.SearchFragment
import org.android.go.sopt.utiil.UtilObject

class MainActivity : AppCompatActivity() {
    private var userData : User? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if(currentFragment == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_main,HomeFragment())
                .commit()
        }
    }
}
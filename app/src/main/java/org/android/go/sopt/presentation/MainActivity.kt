package org.android.go.sopt.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityJoinBinding
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.presentation.home.GalleryFragment
import org.android.go.sopt.presentation.home.HomeFragment
import org.android.go.sopt.presentation.home.SearchFragment

class MainActivity : AppCompatActivity() {
    private var userData : User? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation() {
        binding.naviMain.setOnItemSelectedListener { item->
            changeFragment(
                when(item.itemId){
                    R.id.menu_home->{
                        HomeFragment()
                    }
                    R.id.menu_gallery->{
                        GalleryFragment()
                    }
                    R.id.menu_search->{
                        SearchFragment()
                    }
                    else->{
                        throw IllegalArgumentException("can't find menu item id")
                    }
                }
            )
            true
        }
        binding.naviMain.selectedItemId = R.id.menu_home
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main,fragment)
            .commit()
    }
}
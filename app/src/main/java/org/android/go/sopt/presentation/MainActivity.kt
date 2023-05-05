package org.android.go.sopt.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.presentation.main.gallery.GalleryFragment
import org.android.go.sopt.presentation.main.home.HomeFragment
import org.android.go.sopt.presentation.main.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private var userData : User? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        initBnvReselectedListener()
    }

    private fun initBnvReselectedListener() {
        binding.naviMain.setOnItemReselectedListener {
            val currentFragment = supportFragmentManager.fragments[0]
            if (it.itemId == R.id.menu_home)
                (currentFragment as HomeFragment).scrollToTop()
        }
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
        changeFragment(HomeFragment())
        binding.naviMain.selectedItemId = R.id.menu_home
    }


    private fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main,fragment)
            .commit()
    }
}
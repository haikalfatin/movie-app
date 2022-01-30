 package com.rcyn.made.movie_app.ui.components

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rcyn.made.movie_app.MyApplication
import com.rcyn.made.movie_app.databinding.ActivityMainBinding
import com.rcyn.made.core.ui.base.BaseActivity
import com.rcyn.made.movie_app.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

 class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

     private lateinit var navController: NavController

     @ExperimentalCoroutinesApi
     override fun ActivityMainBinding.onCreate(savedInstanceState: Bundle?) {
         (application as MyApplication).appComponent.inject(this@MainActivity)
         val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
         binding.navView.setupWithNavController(navController)
     }

     override fun observeViewModel() {}
}
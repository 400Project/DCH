package com.oyatech.dch.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

  lateinit  var binding :ActivityDetailBinding
  lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
       setSupportActionBar(binding.detailToolbar)

        //this help the fragment with back stack symbol
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_visit) as NavHostFragment
        navController  = navHost.navController
        setupActionBarWithNavController(navController)

    }


    override fun onDestroy() {
        super.onDestroy()

    }
//set navigation up symbol
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()||super.onSupportNavigateUp()
    }
    /**
     * TODO: DESIGN THE PRESCRIPTION LAYOUT
     */

}
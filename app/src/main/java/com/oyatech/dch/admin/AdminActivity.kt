package com.oyatech.dch.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.ActivityAdminBinding
import com.oyatech.dch.ui.MainActivity

class AdminActivity : AppCompatActivity() {

    private var _binding:ActivityAdminBinding? = null
    private val binding get() = _binding!!
    lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAdminBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_admin) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()|| super.onSupportNavigateUp()
    }

}
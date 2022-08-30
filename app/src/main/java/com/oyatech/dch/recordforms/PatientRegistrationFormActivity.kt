package com.oyatech.dch.recordforms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.oyatech.dch.R
import com.oyatech.dch.database.entities.PatientBioViewModel
import com.oyatech.dch.databinding.ActivityPatientRecordsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel

class PatientRegistrationFormActivity : AppCompatActivity() {
    lateinit var navController: NavController
   lateinit var binding :ActivityPatientRecordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientRecordsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_patient_record) as NavHostFragment
         navController = navHostFragment.navController
     setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()|| super.onSupportNavigateUp()
    }
}
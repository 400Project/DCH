package com.oyatech.dch.patient.recordforms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.oyatech.dch.R
import com.oyatech.dch.consultations.ConsultationViewModel
import com.oyatech.dch.databinding.ActivityPatientRecordsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.patient.data.Particulars

class PatientRegistrationFormActivity : AppCompatActivity() {
    lateinit var navController: NavController
   lateinit var binding :ActivityPatientRecordsBinding

    val viewModel : RegisterNewPatientViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_patient_record) as NavHostFragment
         navController = navHostFragment.navController
     setupActionBarWithNavController(navController)



        /*binding.register.setOnClickListener{
       //     val intent = Intent(this,PatientsDataPage::class.java)



            Log.i("Refistration Finish", "onCreate: $viewModel")
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()|| super.onSupportNavigateUp()
    }

}
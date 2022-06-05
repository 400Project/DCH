package com.oyatech.dch.patient

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import androidx.core.database.DatabaseUtilsCompat
import com.oyatech.dch.R
import com.oyatech.dch.databinding.ActivityPatientRegistrationFormBinding
import com.oyatech.dch.databinding.PatientVitalBinding

class PatientRegistrationFormActivity : AppCompatActivity() {
   lateinit var binding :ActivityPatientRegistrationFormBinding
   val viewModel = RegisterNewPatientViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientRegistrationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.register.setOnClickListener{
            viewModel.getPatientParticulars(binding.patientFirstName.text.toString(),
            binding.patientOtherNames.text.toString())
            finish()
        }
    }


}
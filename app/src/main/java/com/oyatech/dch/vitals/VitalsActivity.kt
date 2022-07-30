package com.oyatech.dch.vitals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.oyatech.dch.R
import com.oyatech.dch.databinding.ActivityVitalsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.patient.RegisterNewPatientViewModel.Companion.particularsList

class VitalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVitalsBinding
    private val viewModel =  RegisterNewPatientViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVitalsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)

                binding.patientFullName.text = particularsList.firstName +" " + particularsList.otherNames
        Toast.makeText(this,"Taking Vitals",Toast.LENGTH_SHORT).show()
            }



}
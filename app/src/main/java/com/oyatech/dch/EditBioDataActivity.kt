package com.oyatech.dch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oyatech.dch.databinding.ActivityEditBioDataBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.patient.data.Particulars

class EditBioDataActivity : AppCompatActivity() {
  private  lateinit var binding: ActivityEditBioDataBinding
    val viewModel = RegisterNewPatientViewModel.viewModel
    lateinit var particulars:Particulars

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBioDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val patientNumber = intent.getIntExtra("details",-1)
        particulars = viewModel.getPatientDetails(patientNumber)
details(particulars)

    }

    fun details(particulars: Particulars){
        with(binding){
        with(particulars){
           hospitalNumberTextView.text = hospitalNumber
            firstnameLayout.setText(firstName)
            otherNamesLayout.setText(otherNames)
            addressLayout.setText(address)
            patientDoB.setText(dob)
            occupationLayout.setText(occupation)
            mobileLayout.setText(mobile)
            nihsLayout.setText(insuranceNumber)
        }

        }
    }

}
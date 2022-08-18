package com.oyatech.dch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.oyatech.dch.databinding.ActivityEditBioDataBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.model.PatientBioData

class EditBioDataActivity : AppCompatActivity() {
  private  lateinit var binding: ActivityEditBioDataBinding
    val viewModel = RegisterNewPatientViewModel.viewModel
    lateinit var patientBioData: PatientBioData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBioDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val patientNumber = intent.getIntExtra("details",-1)
        patientBioData = viewModel.getPatientDetails(patientNumber)
details(patientBioData)

        binding.vitalQue.setOnClickListener {
            viewModel.setQueuedForVitals(patientBioData)
            Toast.makeText(this,"Vital List Updated",Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.done.setOnClickListener {
            Toast.makeText(this,"Record Updated",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun details(patientBioData: PatientBioData){
        with(binding){
        with(patientBioData){
           hospitalNumberTextView.text = hospitalNumber
            firstnameLayout.setText(first_Name)
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
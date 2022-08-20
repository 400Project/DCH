package com.oyatech.dch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.oyatech.dch.databinding.ActivityEditBioDataBinding
import com.oyatech.dch.model.DataSource
import com.oyatech.dch.model.PatientBioData
import com.oyatech.dch.patient.RegisterNewPatientViewModel

class EditBioDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBioDataBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[RegisterNewPatientViewModel::class.java]
    }
    lateinit var patientBioData: PatientBioData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBioDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val patientNumber = intent.getIntExtra("details", -1)
        patientBioData = viewModel.getPatientDetails(patientNumber)
        details(patientBioData)

        binding.vitalQue.setOnClickListener {
            DataSource.addVitalQue(patientBioData)
            Toast.makeText(this, "Vital List Updated", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.done.setOnClickListener {
            Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun details(patientBioData: PatientBioData) {
        with(binding) {
            with(patientBioData) {
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
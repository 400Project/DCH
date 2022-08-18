package com.oyatech.dch.vitals

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oyatech.dch.databinding.ActivityVitalsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.model.PatientBioData


class VitalsActivity : AppCompatActivity() {
    val DUE_FOR_VITALS = "com.oyatech.dch.vitals"
    private lateinit var binding: ActivityVitalsBinding
    private val viewModel = RegisterNewPatientViewModel.viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVitalsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)

        val getVitalIntent = intent.getIntExtra(DUE_FOR_VITALS,-1)
        val currentPatient = viewModel.getCurrentVitalQueue(getVitalIntent)

        bindPatientDetails(currentPatient)

        binding.toConsultation.setOnClickListener {
            viewModel.setQueuedForConsultation(currentPatient)

            Toast.makeText(this, "${currentPatient.first_Name} Vitals", Toast.LENGTH_SHORT).show()

        }
    }

    private fun bindPatientDetails(patient: PatientBioData){
        with(binding){
            with(patient){
                patientFullName.text = "$first_Name $otherNames"
                patientAge.text = age
                patientHospitalNumber.text = hospitalNumber
                patientAddress.text = address
                patientGender.text = sex
                patientDoB.text = dob
                patientNhis.text = insuranceNumber
                patientMobile.text = mobile


            }

        }
    }


}
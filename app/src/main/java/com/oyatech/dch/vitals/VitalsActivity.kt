package com.oyatech.dch.vitals

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oyatech.dch.databinding.ActivityVitalsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.patient.data.Particulars


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

        bindPatientDetials(currentPatient)

        binding.toConsultation.setOnClickListener {

            Toast.makeText(this, "${currentPatient.firstName} Vitals", Toast.LENGTH_SHORT).show()

        }
    }

    fun bindPatientDetials(patient:Particulars){
        with(binding){
            with(patient){
                patientFullName.text = "$firstName $otherNames"
                patientAge.text = age
                patientHospitalNumber.text = hospitalNumber
                patientAddress.text = address
                patientGender.text = sex
                patientDoB.text = dob
                insuranceText.text = insuranceNumber
                patientMobile.text = mobile


            }

        }
    }


}
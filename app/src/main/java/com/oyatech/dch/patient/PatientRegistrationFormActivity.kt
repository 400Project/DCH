package com.oyatech.dch.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.oyatech.dch.consultations.ConsultationViewModel
import com.oyatech.dch.databinding.ActivityPatientRegistrationFormBinding
import com.oyatech.dch.patient.data.Particulars

class PatientRegistrationFormActivity : AppCompatActivity() {
   lateinit var binding :ActivityPatientRegistrationFormBinding
    val queBinding  = ConsultationViewModel()
    val viewModel : RegisterNewPatientViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientRegistrationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

       
        binding.register.setOnClickListener{
       //     val intent = Intent(this,PatientsDataPage::class.java)

            val firstName = binding.patientFirstName.text.toString().trim()
            val otherName = binding.patientOtherNames.text.toString().trim()
            val patient = Particulars(firstName,otherName)
           viewModel.getConsultationQue(patient)
            finish()

            Log.i("Refistration Finish", "onCreate: $viewModel")
        }
    }


}
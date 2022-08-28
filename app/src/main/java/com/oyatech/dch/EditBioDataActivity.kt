package com.oyatech.dch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.PatientBioViewModel
import com.oyatech.dch.databinding.ActivityEditBioDataBinding


class EditBioDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBioDataBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[PatientBioViewModel::class.java]
    }

    var primaryKey =0

    private var _patientBioData: PatientBioData?= null
    private val patientBioData get() = _patientBioData!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBioDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


val viewModel= viewModel
        //Getting the selected patient details
        var patientNumber = intent.getIntExtra("details", -1)
        patientNumber += 1
       _patientBioData= viewModel.currentBio(patientNumber)
        details(patientBioData)


val dVitals = DailyVitals(primaryKey,patientBioData)


        binding.vitalQue.setOnClickListener {
            //Adding patient to the vital queue
         viewModel.queueForVitals(dVitals)
            Toast.makeText(this, "Vital List Updated", Toast.LENGTH_SHORT).show()
            finish()
        }


        binding.done.setOnClickListener {
            Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    //TODO: RETURN A PARTICULAR PATIENT BASED ON IT VALUE IN THE QUEUE

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
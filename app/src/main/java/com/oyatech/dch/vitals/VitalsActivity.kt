package com.oyatech.dch.vitals

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.Vitals
import com.oyatech.dch.databinding.ActivityVitalsBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity
import com.oyatech.dch.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VitalsActivity : AppCompatActivity() {
    val DUE_FOR_VITALS = "com.oyatech.dch.vitals"
    private lateinit var binding: ActivityVitalsBinding
    private var primaryKey = 0
    private var foreignKey = 0
    val dateTime = Utils.getDateAndTime()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVitalsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[VitalsViewModel::class.java]
        val currentPatientPos = intent.getIntExtra(DUE_FOR_VITALS, -1)
        val currentPatient = viewModel.getCurrentPatientForVitals(currentPatientPos)
        foreignKey = currentPatient.patientId

        /**
         * passing the patient object to be bind to the views
         */
        bindPatientDetails(currentPatient)

        binding.toConsultation.setOnClickListener {
            lifecycleScope.launch {
                Dispatchers.IO
                viewModel.apply {
                    insertVitals(getVitals())
                    bookForConsultation(getPatientForConsult(currentPatient))
                }
            }
            Toast.makeText(
                this,
                "${currentPatient.first_Name} booked for consultation",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(this, PatientsDataPageActivity::class.java))
            finish()
        }
    }

    private fun bindPatientDetails(patient: PatientBioData) {
        with(binding) {
            with(patient) {
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


    private fun getVitals(): Vitals {
        with(binding) {
            val bloodPressure = bloodPressure.text.toString().trim()
            val weight = patientWeight.text.toString().trim()
            val temperature = temperature.text.toString().trim()
            val sugarLevel = sugerLevel.text.toString().trim()
            return Vitals(
                primaryKey, foreignKey, bloodPressure,
                weight, temperature, sugarLevel
            )
        }

    }

    private fun getPatientForConsult(bioData: PatientBioData): DailyConsultation {
        val primaryKey = 0
        return DailyConsultation(primaryKey,bioData)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("Vitals", "onSaveInstanceState: state in null")


        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState != null) {

            Log.i("Vitals", "onSaveInstanceState: state in not null")
        }
        super.onRestoreInstanceState(savedInstanceState)
    }
}
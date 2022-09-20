package com.oyatech.dch.vitals

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.oyatech.dch.database.entities.*
import com.oyatech.dch.databinding.ActivityVitalsBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VitalsActivity : AppCompatActivity() {
    val DUE_FOR_VITALS = "com.oyatech.dch.vitals"
    private lateinit var binding: ActivityVitalsBinding
    private var current = 0
    private var previous =0
    private var patientId = 0

    val viewModel by lazy {
        ViewModelProvider(this)[VitalsViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVitalsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        val currentPatientPos: Int = intent.getIntExtra(DUE_FOR_VITALS, -1)
        val currentPatient = PatientVitalsFragment.vitalQueue[currentPatientPos]
        patientId = currentPatient!!.patientId


    previous = viewModel.getVitalsIDs()


        /**
         * passing the patient object to be bind to the views
         */
        bindPatientDetails(currentPatient)
        /**
         * TODO: SAVE TO VITALS
         */
        binding.toConsultation.setOnClickListener {
            lifecycleScope.launch {
            //    val v: DailyVitals = viewModel.getCurrentQueVitals(foreignKey)
                Dispatchers.IO
                viewModel.apply {
               //     insertVitals(getVitals())
                    insertVitalsOnline(getVitals())
                    val d = ViDs(current)
                    if (current==1){
                        insertVitalsIDs(d)
                    }else{
                        updateVitalsIDs(previous,current)
                    }
                    //book for consultation
                   insertDailyConsultation(
                       getPatientForConsult(
                           currentPatientPos,currentPatient)
                   )
                    //remove for vital queue
                   removePatientFromVitalsQue(currentPatientPos)

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
                patientGender.text = gender
                patientDoB.text = dob
                patientNhis.text = insuranceNumber
                patientMobile.text = mobile


            }

        }
    }


    private fun getVitals(): Vitals {
        with(binding) {
            current = previous + 1
            val bloodPressure = bloodPressure.text.toString().trim()
            val weight = patientWeight.text.toString().trim()
            val temperature = temperature.text.toString().trim()
            val sugarLevel = sugerLevel.text.toString().trim()
            val vitals = Vitals(
            current, patientId, bloodPressure,
            weight, temperature, sugarLevel
            )

            return vitals
        }

    }

    private fun getPatientForConsult(postiton: Int,
                                     bioData: PatientBioData)
    : DailyConsultation {
        return DailyConsultation(postiton,bioData)
    }

}
package com.oyatech.dch.vitals

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.oyatech.dch.database.entities.PatientBioViewModel
import com.oyatech.dch.database.entities.Vitals

import com.oyatech.dch.databinding.ActivityVitalsBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity
import com.oyatech.dch.database.entities.PatientBioData


class VitalsActivity : AppCompatActivity() {
    val DUE_FOR_VITALS = "com.oyatech.dch.vitals"
    private lateinit var binding: ActivityVitalsBinding
private var primaryKey =0
    private var foreignKey =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVitalsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

         val viewModel = ViewModelProvider(this)[VitalsViewModel::class.java]
        val currentPatientPos = intent.getIntExtra(DUE_FOR_VITALS,-1)
      val currentPatient = viewModel.getCurrentPatientForVitals(currentPatientPos)

        /**
         * passing the patient object to be bind to the views
         */
       bindPatientDetails(currentPatient)

        binding.toConsultation.setOnClickListener {

         //   viewModel.insertVitals(getVitals())
startActivity(Intent(this,PatientsDataPageActivity::class.java))


      //      Toast.makeText(this, "${currentPatient.first_Name} booked for consultation", Toast.LENGTH_SHORT).show()
finish()
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


    private fun getVitals():Vitals{
       with(binding){
            val bloodPressure = bloodPressure.text.toString().trim()
            val weight = patientWeight.text.toString().trim()
            var temperature = temperature.text.toString().trim()
           val sugarLevel = sugerLevel.text.toString().trim()
           val vitals = Vitals(primaryKey,foreignKey
               ,"12/4/2022",bloodPressure,
           weight,temperature,sugarLevel)

          return vitals
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("Vitals", "onSaveInstanceState: state in null")


        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if(savedInstanceState!=null){

            Log.i("Vitals", "onSaveInstanceState: state in not null")
        }
        super.onRestoreInstanceState(savedInstanceState)
    }
}
package com.oyatech.dch.vitals

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.oyatech.dch.R

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

    override fun onStart() {
        super.onStart()

    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVitalsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val myViewModel = viewModel
       //make temperature unit a superscript
makeSuperScript()


        val currentPatientPos: Int = intent.getIntExtra(DUE_FOR_VITALS, -1)
        val currentPatient = PatientVitalsFragment.vitalQueue[currentPatientPos]
        patientId = currentPatient!!.patientId

        //getting the previous vitals id from remote


        /**
         * passing the patient object to be bind to the views
         */
        bindPatientDetails(currentPatient)
        /**
         * TODO: SAVE TO VITALS
         */
       myViewModel.getVitalsId().observe(this){
           previous = it
        }
        binding.toConsultation.setOnClickListener {
            myViewModel.apply {

                lifecycleScope.launch {
               //     insertVitals(getVitals())
                    insertVitalsOnline(getVitals())

                        insertVitalId(ViDs(current))

                    //book for consultation
                    removePatientFromVitalsQue(currentPatientPos)
                    //remove from vital queue
                    try { val forConsult = DailyConsultation(currentPatientPos,currentPatient)
                        insertDailyConsultation(forConsult)


                    }catch (e:Exception){
                        Log.i("Vitals", "onCreate: ${e.message}")
                    }

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


@RequiresApi(Build.VERSION_CODES.N)
private fun makeSuperScript(){
    val hint : String = getString(R.string.temperature)
    val sytledHint: Spanned = Html.fromHtml(hint,FROM_HTML_MODE_LEGACY)
    binding.temperatureLayout.hint = sytledHint}
}
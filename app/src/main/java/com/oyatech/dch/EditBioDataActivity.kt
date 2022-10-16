package com.oyatech.dch

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.NextOfKin
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.patient.PatientBioViewModel
import com.oyatech.dch.databinding.ActivityEditBioDataBinding
import com.oyatech.dch.patient.PatientBioFragment
import com.oyatech.dch.util.Utils


class EditBioDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBioDataBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[PatientBioViewModel::class.java]
    }
    private val PRIMARY_KEY = "patient_primary_key"
    var primaryKey =0

    private var _patientBioData: PatientBioData?= null
    private val patientBioData get() = _patientBioData!!

    var isShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBioDataBinding.inflate(layoutInflater)

        setContentView(binding.root)


val viewModel= viewModel

        //Getting the selected patient details
        val patientNumber = intent.getIntExtra("details", -1)

       _patientBioData= PatientBioFragment.tree[patientNumber]
        details(patientBioData)




        binding.vitalQue.setOnClickListener {
            //Adding patient to the vital queue

            val dVitals = DailyVitals(patientNumber,patientBioData)
         viewModel.insertDailyVitals(dVitals)
            Toast.makeText(this, "Vital List Updated", Toast.LENGTH_SHORT).show()

            finish()
        }


        binding.update.setOnClickListener {
          //  val nextOfKin = nextOfKinDetails()
            
            Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show()

        }

        binding.apply {
            hideOrShow.setOnClickListener {

                when(isShown){
                    false ->  {
                        nextKinLayout.visibility = View.VISIBLE
                        hideOrShow.setImageResource(R.drawable.ic_arrow_up_24)
                        isShown = true
                    }else -> {
                    nextKinLayout.visibility = View.GONE
                    hideOrShow.setImageResource(R.drawable.ic_arrow_down_24)
                    isShown = false
                    }


                }


                Toast.makeText(this@EditBioDataActivity,"Show",Toast.LENGTH_LONG * 3).show()
            }
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
/*
    private fun nextOfKinDetails():NextOfKin{
        with(binding){
         val fullName =   nextOfKingName.text.toString().trim()
         val relationship =   nofKRelationshipLayout.text.toString().trim()
         val mobile =   nofKMobile.text.toString().trim()
            val address = nofKAddress.text.toString().trim()
            val occupation = occupation.text.toString().trim()
            return NextOfKin(primaryKey,fullName,relationship,mobile,address,occupation)
        }

    }*/

}
package com.oyatech.dch.recordforms

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.util.rangeTo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.oyatech.dch.R
import com.oyatech.dch.patient.PatientBioViewModel
import com.oyatech.dch.databinding.FragmentBioDataBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.util.Utils
import java.util.*


class PatientRegistrationFragment : Fragment() {
//View Binding
 private   var _binding:  FragmentBioDataBinding? = null
   private val binding get() = _binding!!

    val viewModel  : PatientBioViewModel by activityViewModels()
     var primaryKey:Int = 0

    private val calender: Calendar = Calendar.getInstance()
   private var patientYear=0
   lateinit var sex: String



 //   private val viewModel : RegisterNewPatientViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          _binding = FragmentBioDataBinding.inflate(LayoutInflater.from(context)
            ,container,false)

        return binding.root.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        primaryKey = activity?.intent?.getIntExtra(PRIMARY_KEY,0)!!

        Log.i("Regid", "onCreate: is called")
        //Phone number formatter according to Ghana local

       formatPhoneNumber()
val myViewModel = viewModel
        binding.patientDoB.setOnClickListener{

            //getting the calender by using the DatePickerDialog
       pickDate()

            //Computing the current age of the patient using the different in the current year and
           // the year of the his dob
            val currentYear = calender.get(Calendar.YEAR)
            val age = currentYear - patientYear
            if (age>100){
                binding.patientDoB.setError("Select Date of Birth")
                binding.patientAge.text = " yrs"
            }else
            binding.patientAge.text = age.toString()+"yrs"
        }

        binding.patientSex.onItemSelectedListener = getPatientSex()

        Toast.makeText(context,"Spinner",Toast.LENGTH_SHORT).show()
        //Navigate to the next of kin fragment
binding.next.setOnClickListener {
    findNavController().navigate(R.id.action_bioDataFragment_to_nextOfFragment)
}

        //register new patient to the hospital records database
        binding.done.setOnClickListener {
            addNewPatient()
            startActivity(Intent(this.context,PatientsDataPageActivity::class.java))
            activity?.finish()
        }
    }

    private fun getPatientSex():AdapterView.OnItemSelectedListener {
//The createFromResource() method allows you to create an ArrayAdapter from the string array.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sex,
            android.R.layout.simple_spinner_item
            /**
             * You should then call setDropDownViewResource(int) to specify the layout the adapter
             * should use to display the list of spinner choice
             */
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            binding.patientSex.adapter = arrayAdapter
//Responding to user selection
        val item: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapteView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    sex = adapteView?.getItemAtPosition(position).toString()
                    Log.i("Sinner", "onItemSelected: ${adapteView?.getItemAtPosition(position)}")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            return item

        }
    }

    //Creating new patientBioData object
    @RequiresApi(Build.VERSION_CODES.O)
    private fun addNewPatient() {

        with(binding){

            val hospitalNumber = Utils.generateHospitalNumber(primaryKey)

            val date = Utils.getDate()
           val sex = sex

            val firstName = patientFirstName.text.toString().trim()
            val otherName = patientOtherNames.text.toString().trim()
            val address =patientAddress.text.toString().trim()
            val dob =patientDoB.text.toString().trim()
            val age = computeDateOfBirth(calender.get(Calendar.YEAR),patientYear)
            val occupation=patientOccupation.text.toString().trim()
            val mobile =patientMobile.text.toString().trim()
            val nhis =patientNhis.text.toString().trim()

                val patientBioData = PatientBioData(primaryKey
                    ,hospitalNumber,
                    firstName, otherName,
                    address,dob,age,sex,
                    occupation,mobile,nhis,date,
                    Utils.getTime())

            viewModel.insertPatientFirestore(patientBioData)
            Toast.makeText(requireContext(),"Patient Added",Toast.LENGTH_SHORT).show()

        }

    }

    //Getting the date from the date picker
    @SuppressLint("SuspiciousIndentation")
    private fun pickDate() {

        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
     DatePickerDialog(requireContext(),
        { datePicker, year, month, day ->
            val monthOfBirth = month +1
            patientYear = year
          binding.patientDoB.setText("$day/$monthOfBirth/$year")
            if (year<1910){
                binding.patientDoB.error = "Select DOB"
            }else
            binding.patientAge.text = (calender.get(Calendar.YEAR)-year).toString()+"yrs"
      },year,month,day).show()

    }

    private fun computeDateOfBirth(yearOfBirth:Int,currentYear:Int):String{
        val age =  yearOfBirth - currentYear
        return "$age yrs"
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun formatPhoneNumber(){
        with(binding.patientMobile){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                this.addTextChangedListener(PhoneNumberFormattingTextWatcher(Locale.getDefault().country))
            }else{

                this.addTextChangedListener{
                    text: Editable? -> text?.replace(0,text.length,PhoneNumberUtils.formatNumber(text.toString()))
                }
            }
        }

    }


    private val PRIMARY_KEY = "patient_primary_key"



    override fun onPause() {
        Log.i("Regid", "onPause: is called")
primaryKey
        super.onPause()
    }
}
package com.oyatech.dch.recordforms

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.alerts.isEmptyView
import com.oyatech.dch.alerts.toaster
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.databinding.FragmentBioDataBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity
import com.oyatech.dch.patient.PatientBioViewModel
import com.oyatech.dch.util.Utils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import com.oyatech.dch.alerts.trimText


class PatientRegistrationFragment : Fragment() {
    //View Binding
    private var _binding: FragmentBioDataBinding? = null
    private val binding get() = _binding!!

    companion object {
        var patientBioData = PatientBioData()
    }
    private val PRIMARY_KEY = "patient_primary_key"


    val viewModel: PatientBioViewModel by activityViewModels()
    var primaryKey: Int = 0

    private val calender: Calendar = Calendar.getInstance()
    private var patientYear = 0
    lateinit var sex: String


    //   private val viewModel : RegisterNewPatientViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBioDataBinding.inflate(
            LayoutInflater.from(context), container, false
        )
        return binding.root.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        primaryKey = activity?.intent?.getIntExtra(PRIMARY_KEY, 0)!!

        Log.i("Regid", "onCreate: is called")
        //Phone number formatter according to Ghana local

        formatPhoneNumber()

        binding.patientDoB.setOnClickListener {

            //getting the calender by using the DatePickerDialog
            pickDate()

            //Computing the current age of the patient using the different in the current year and
            // the year of the his dob
            val currentYear = calender.get(Calendar.YEAR)
            val age = currentYear - patientYear
            if (age > 100) {
                binding.patientDoB.setError("Select Date of Birth")
                binding.patientAge.text = " yrs"
            } else {
                binding.patientAge.text = age.toString() + "yrs"

            }
        }

        binding.patientSex.onItemSelectedListener = getPatientSex()

        //Navigate to the next of kin fragment
        binding.next.setOnClickListener {
            binding.apply {
                //Making sure all data is filled before moving to next of kin data page

                val hospitalNumber = Utils.generateHospitalNumber(primaryKey)

                val date = Utils.getDate()
                val sex = sex

                val firstName = trimText(patientFirstName)
                val otherName = trimText(patientOtherNames)
                val address = trimText(patientAddress)
                val dob =  patientDoB.text.toString().trim()
                val age = computeDateOfBirth(calender.get(Calendar.YEAR), patientYear)
                val occupation = trimText(patientOccupation)
                val mobile = trimText(patientMobile)
                val nhis = trimText(patientNhis)
                if ((isEmptyView(patientFirstName)) ||
                    (isEmptyView(patientOtherNames)) ||
                    (isEmptyView(patientAddress)) ||
                    (isEmptyView(patientOccupation)) ||
                    (isEmptyView(patientMobile))
                ) {
                    requireContext().toaster("Fields can't be empty")
                } else {
                    patientBioData = PatientBioData(
                        primaryKey, hospitalNumber,
                        firstName, otherName,
                        address, dob, age, sex,
                        occupation, mobile, nhis, date,
                        Utils.getTime()
                    )

                    findNavController().navigate(R.id.action_bioDataFragment_to_nextOfFragment)
                }

            }

        }

        //register new patient to the hospital records database
        binding.done.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            if (!addNewPatient()) {

                //Intentionally sleeping thread for 3 second ): ):
                lifecycleScope.launch {
                    kotlin.run {
                        delay(5000)
                        binding.progressBar.visibility = View.INVISIBLE
                        startActivity(
                            Intent(
                                requireContext(),
                                PatientsDataPageActivity::class.java
                            )
                        )
                        activity?.finish()
                    }

                }


            }

        }
    }

    private fun getPatientSex(): AdapterView.OnItemSelectedListener {
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
            val item: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapteView: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        p3: Long
                    ) {
                        sex = adapteView?.getItemAtPosition(position).toString()
                        Log.i(
                            "Sinner",
                            "onItemSelected: ${adapteView?.getItemAtPosition(position)}"
                        )
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }
            return item

        }
    }

    //Creating new patientBioData object
    private fun addNewPatient(): Boolean {

        with(binding) {

            val hospitalNumber = Utils.generateHospitalNumber(primaryKey)

            val date = Utils.getDate()
            val sex = sex

            val firstName = trimText(patientFirstName)
            val otherName = trimText(patientOtherNames)
            val address = trimText(patientAddress)
            val dob =  patientDoB.text.toString().trim()
            val age = computeDateOfBirth(calender.get(Calendar.YEAR), patientYear)
            val occupation = trimText(patientOccupation)
            val mobile = trimText(patientMobile)
            val nhis = trimText(patientNhis)
            if ((isEmptyView(patientFirstName)) ||
                (isEmptyView(patientOtherNames)) ||
                (isEmptyView(patientAddress)) ||
                (isEmptyView(patientOccupation)) ||
                (isEmptyView(patientMobile))
            ) {
                requireContext().toaster("Fields can't be empty")
                return true
            } else {
                patientBioData = PatientBioData(
                    primaryKey, hospitalNumber,
                    firstName, otherName,
                    address, dob, age, sex,
                    occupation, mobile, nhis, date,
                    Utils.getTime()
                )

                viewModel.insertPatientFirestore(patientBioData)

                Toast.makeText(requireContext(), "$firstName Added", Toast.LENGTH_SHORT).show()
                return false
            }

        }

    }

    //Getting the date from the date picker
    private fun pickDate() {

        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            requireContext(),
            { datePicker, year, month, day ->
                val monthOfBirth = month + 1
                patientYear = year
                binding.patientDoB.setText("$day/$monthOfBirth/$year")
                if (year < 1910) {
                    binding.patientDoB.error = "Select DOB"
                } else {
                    binding.patientAge.text =
                        (calender.get(Calendar.YEAR) - year).toString() + "yrs"

                }
            }, year, month, day
        ).show()

    }

    private fun computeDateOfBirth(yearOfBirth: Int, currentYear: Int): String {
        val age = yearOfBirth - currentYear
        return "$age yrs"
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun formatPhoneNumber() {
        with(binding.patientMobile) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.addTextChangedListener(PhoneNumberFormattingTextWatcher(Locale.getDefault().country))
            } else {

                this.addTextChangedListener { text: Editable? ->
                    text?.replace(0, text.length, PhoneNumberUtils.formatNumber(text.toString()))
                }
            }
        }

    }



    override fun onPause() {
        Log.i("Regid", "onPause: is called")
        super.onPause()
    }


}
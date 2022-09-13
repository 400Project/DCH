package com.oyatech.dch.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.Tests
import com.oyatech.dch.Tests.*
import com.oyatech.dch.consultations.ConsultationViewModel
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.Vitals
import com.oyatech.dch.databinding.FragmentDignosesBinding


class DiagnosesFragment : Fragment() {

    private var _binding: FragmentDignosesBinding? = null

    private val binding get() = _binding!!

    val viewModel: ConsultationViewModel by activityViewModels()

    private var isTestLayoutVisible = true
    var numberOfTest = SECOND
    lateinit var treatStatus: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDignosesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = viewModel
        viewModel.apply {

            bindVitalViews(getCurrentVitals(position))
        }
        //    val vitals = viewModel.getCurrentVitals(viewModel.position)
        with(binding) {
            showHideIcon.setOnClickListener {
                showHidLayout(testLayout)
            }
        }

        binding.submit.setOnClickListener {
            Toast.makeText(requireContext(), "Diagnosed", Toast.LENGTH_SHORT).show()
           viewModel.apply {
               val consultation = getDailConsultationByID(position)
               val diagnose = diagnoseObject(position)
              insertDiagnosis(diagnose)
               if ((treatStatus == "Discharged")
                   || (treatStatus == "Transferred")
                   ||(treatStatus=="Died")){
                   removeFromDailyConsultation(consultation)
               }

           }
            findNavController().navigate(R.id.medicalHistoryFragment)
        }

binding.treatmentStatus.onItemSelectedListener = getTreatmentStatus()
    }

    //Creating the number of test layout for test to be conducted
    private fun createNumberOfTest(testNumber: Tests) {
        with(binding) {
            when (testNumber) {
                SECOND -> {
                    test2.visibility = VISIBLE
                    numberOfTest = THIRD

                }
                THIRD -> {
                    test3.visibility = VISIBLE
                    numberOfTest = FOURTH
                }
                FOURTH -> {
                    test4.visibility = VISIBLE
                    numberOfTest = FIFTH

                }
                FIFTH -> {
                    test5.visibility = VISIBLE

                }
                else -> Toast.makeText(requireContext(), "Test Limit reach", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun bindVitalViews(vitals: Vitals) {
        with(binding) {
            vitals.apply {
                patientTemperature.text = bodyTemperature
                patientWeight.text = weight
                patientBloodPressure.text = bloodPressure
                patientSugarLevel.text = sugarLevel
            }
        }
    }

    private fun showHidLayout(testLayout: ConstraintLayout) {
        binding.apply {
            with(testLayout) {
                when (isTestLayoutVisible) {
                    false -> {
                        visibility = GONE
                        showHideTest.text = "Show Tests"
                        showHideIcon.setImageResource(R.drawable.ic_arrow_down_24)
                        isTestLayoutVisible = true
                    }
                    else -> {
                        visibility = VISIBLE
                        showHideTest.text = "Hide Tests"
                        isTestLayoutVisible = false
                        showHideIcon.setImageResource(R.drawable.ic_arrow_up_24)
                        add.setOnClickListener {
                            createNumberOfTest(numberOfTest)

                        }
                    }
                }
            }
        }
    }

    private fun diagnoseObject(position: Int): Diagnose {
        val primaryKey = 0
        binding.apply {
            val treatment = treatStatus
            val provisional = provisionalDiagnosis.text.toString().trim()
            val principal = pricipalDiagnosis.text.toString().trim()
            val additional = additionalDiagnosis.text.toString().trim()
            // val prescription= prescription.text.toString().trim()
            var nurseNote = nurseNote.text.toString().trim()
            val tests = firstTest.text.toString().trim()
            return Diagnose(
                primaryKey, position, provisional, principal, additional,
                nurseNote,"Dr.Robert",treatment
            )
        }
    }
    //TODO: populate ward and dispensary page with patient

    private fun getTreatmentStatus(): AdapterView.OnItemSelectedListener
    {
//The createFromResource() method allows you to create an ArrayAdapter from the string array.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.patient_status,
            android.R.layout.simple_spinner_item
            /**
             * You should then call setDropDownViewResource(int) to specify the layout the adapter
             * should use to display the list of spinner choice
             */
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            binding.treatmentStatus.adapter = arrayAdapter
//Responding to user selection
            val item: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapteView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    treatStatus = adapteView?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            return item

        }
    }
}
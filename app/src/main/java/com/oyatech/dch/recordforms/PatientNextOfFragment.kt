package com.oyatech.dch.recordforms

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.oyatech.dch.R
import com.oyatech.dch.alerts.isEmptyView
import com.oyatech.dch.alerts.snackForError
import com.oyatech.dch.database.entities.NextOfKin
import com.oyatech.dch.databinding.FragmentNextOfKingBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity
import com.oyatech.dch.patient.PatientBioViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [PatientNextOfFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PatientNextOfFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentNextOfKingBinding? = null
    private val binding get() = _binding!!


    private var primaryKey = 0
    val viewModel: PatientBioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNextOfKingBinding.inflate(layoutInflater, container, false)

        return binding.root.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerNextOfKing.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            if (!getNextOfKingDetails()) {
                lifecycleScope.launch {
                    kotlin.run {
                        delay(5000)
                        binding.progressBar.visibility = View.INVISIBLE
                        requireContext().snackForError(view, "Patient Registered")
                        findNavController().navigate(R.id.action_bioDataFragment_to_nextOfFragment)
                    }

                }

            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun getNextOfKingDetails(): Boolean {
        primaryKey += (2..1000).random()
        val patient = PatientRegistrationFragment.patientBioData

        val patientId = patient.patientId
        with(binding) {
            val nxtKFullName = kinsFullName.text.toString().trim()
            val mobile = nextOfKingMobile.text.toString().trim()
            val relation = nextOfKingRelationship.text.toString().trim()
            val occupation = nextOfKingOccupation.text.toString().trim()
            val address = nextOfAddress.text.toString().trim()
            //Empty exception handling
            if ((isEmptyView(kinsFullName)) ||
                (isEmptyView(nextOfKingMobile)) ||
                (isEmptyView(nextOfKingRelationship)) ||
                (isEmptyView(nextOfKingOccupation)) ||
                (isEmptyView(nextOfAddress))
            ) {
                return true
            } else {
                val nextOfKin = NextOfKin(
                    primaryKey,
                    patientId,
                    nxtKFullName,
                    mobile,
                    relation,
                    occupation,
                    address
                )

                viewModel.insertPatientFirestore(patient)
                viewModel.insertNextOfKin(nextOfKin)

                return false

            }

            /**
             * TODO: find out why the break isn't working
             */
        }
    }

    private fun trimMaker(editText: TextInputEditText): String {
        return editText.text.toString().trim()
    }
}
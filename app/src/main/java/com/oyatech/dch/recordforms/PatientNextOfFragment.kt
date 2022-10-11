package com.oyatech.dch.recordforms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.alerts.isEmptyView
import com.oyatech.dch.alerts.snackForError
import com.oyatech.dch.database.entities.NextOfKin
import com.oyatech.dch.databinding.FragmentNextOfKingBinding
import com.oyatech.dch.patient.PatientBioViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.oyatech.dch.alerts.trimText


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
                        findNavController().navigate(R.id.action_nextOfFragment_to_bioDataFragment)
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
            val nxtKFullName = trimText(kinsFullName)
            val mobile = trimText(kinsFullName)
            val relation = trimText(nextOfKingRelationship)
            val occupation = trimText(nextOfKingOccupation)
            val address = trimText(nextOfAddress)
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

        }
    }


}
package com.oyatech.dch.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentVisitsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.model.PatientBioData

/**
 * A simple [Fragment] subclass.
 * Use the [VisitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisitsFragment : Fragment() {
    private final val PATIENT_VISITS = "com.oyatech.dch.details"
    val viewModel = RegisterNewPatientViewModel.viewModel
    private var _binding: FragmentVisitsBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVisitsBinding.inflate(inflater, container, false)


        return binding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getting the position of the patient in the consultation room
        val pPosition = requireActivity().intent.getIntExtra(PATIENT_VISITS, -1)
        //getting he/her medical history
        val patient = viewModel.getCurrentQueuedForConsultation(pPosition)
        //setting all views with his/her details
        bindPatientDetails(patient)

        //Intending to add diagnoses & prescription
        binding.addPatientVitals.setOnClickListener {
           findNavController().navigate(R.id.dignosesFragment)
        }

    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after [.onStop] and before [.onDetach].
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.visits, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.vital -> findNavController().navigate(R.id.detailRecordFragment)
        }
        return true
    }

    fun bindPatientDetails(patient: PatientBioData) {
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
}
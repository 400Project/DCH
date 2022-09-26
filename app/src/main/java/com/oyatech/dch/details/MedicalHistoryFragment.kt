package com.oyatech.dch.details

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.R
import com.oyatech.dch.consultations.ConsultationsFragment
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.databinding.FragmentMedicalHistoryBinding
import kotlinx.coroutines.launch
import java.util.*

class MedicalHistoryFragment : Fragment() {
    private final val PATIENT_VISITS = "com.oyatech.dch.details"

    private var _binding: FragmentMedicalHistoryBinding? = null

    private val binding get() = _binding!!

    companion object {
        var diagnosis: TreeMap<Int, Diagnose> = TreeMap()
    }
    var patientId: Int = 0

    val viewModel : MedicalHistoryViewModel by activityViewModels()
    val myAdapter by lazy {
        MedicalHistoryAdapter(requireContext(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMedicalHistoryBinding.inflate(inflater, container, false)


        return binding.root
        // Inflate the layout for this fragment

    }

    private val medicalHistoryAdapter: MedicalHistoryAdapter
        get() {
            var adapter = myAdapter
            return adapter
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = medicalHistoryAdapter
        //getting the position of the patient in the consultation room
        patientId = requireActivity().intent.getIntExtra(PATIENT_VISITS, -1)

        //getting he/her medical history
        val patient = ConsultationsFragment.trees[patientId]!!

        val layoutManager = LinearLayoutManager(requireContext())
        binding.visitsRecycleView.apply {
            viewModel.fetchDiagnosis(patientId).observe(viewLifecycleOwner) { it ->
                if (it.isEmpty()) {
                    binding.noMedic.visibility = View.VISIBLE
                    binding.noMedicals.visibility = View.VISIBLE
                }
                setLayoutManager(layoutManager)
                adapter.submitList(it)

                //setting recycle adapter
                adapter = adapter

                viewModel.setPosition(patientId)
                lifecycleScope.launch {

                    it.forEach {
                        diagnosis[it.diagnoseID] = it
                    }
                }
            }
        }

        //setting vitals view with the appropriate views
        bindPatientDetails(patient)

        //updating the recycleView
    //    createRecycleViewer()

        binding.addPatientDiagnosis.setOnClickListener {

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

    private fun createRecycleViewer() {

    }

    override fun onResume() {
        Log.i("Medical History", "onPause: is called")
        binding.visitsRecycleView.apply {
            viewModel.fetchDiagnosis(patientId).observe(viewLifecycleOwner) {
                medicalHistoryAdapter.submitList(it)
                adapter = medicalHistoryAdapter
            }
        }
        super.onResume()
    }

    override fun onPause() {
        Log.i("Medical History", "onPause: is called")
        super.onPause()

    }
}
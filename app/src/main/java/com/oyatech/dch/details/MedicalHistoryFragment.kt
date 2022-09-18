package com.oyatech.dch.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.R
import com.oyatech.dch.consultations.ConsultationsFragment
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.databinding.FragmentMedicalHistoryBinding

class MedicalHistoryFragment : Fragment() {
    private final val PATIENT_VISITS = "com.oyatech.dch.details"

    private var _binding: FragmentMedicalHistoryBinding? = null

    private val binding get() = _binding!!

    private var patientId:Int =0

    val viewModel: MedicalHistoryViewModel by activityViewModels()

    val myAdapter by lazy {
        MedicalHistoryAdapter(requireContext(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMedicalHistoryBinding.inflate(inflater, container, false)


        return binding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getting the position of the patient in the consultation room
        patientId = requireActivity().intent.getIntExtra(PATIENT_VISITS, -1)
        //getting he/her medical history
        val patient = ConsultationsFragment.trees[patientId]!!

        //setting vitals view with the appropriate views
        bindPatientDetails(patient)

       //updating the recycleView
      createRecycleViewer()

        binding.addPatientDiagnosis.setOnClickListener {

            //passing the current patient id to used to locate the vitals
            viewModel.setPosition(patient.patientId)

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

 private  fun createRecycleViewer (){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.visitsRecycleView.apply {
            viewModel.fetchDiagnosis(patientId).observe(viewLifecycleOwner) {
                setLayoutManager(layoutManager)
                myAdapter.submitList(it)

                //setting recycle adapter
                adapter = myAdapter

                if (it.isEmpty()) {
                    binding.noMedic.visibility = View.VISIBLE
                    binding.noMedicals.visibility = View.VISIBLE
                }

            }
        }

 }

    override fun onResume() {
        binding.visitsRecycleView.apply {
            viewModel.fetchDiagnosis(patientId).observe(viewLifecycleOwner){
                adapter = myAdapter
            }
        }
        super.onResume()
    }
}
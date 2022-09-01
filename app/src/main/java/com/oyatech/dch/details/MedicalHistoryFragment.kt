package com.oyatech.dch.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.R
import com.oyatech.dch.consultations.ConsultationViewModel
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.databinding.FragmentMedicalHistoryBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MedicalHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicalHistoryFragment : Fragment() {
    private final val PATIENT_VISITS = "com.oyatech.dch.details"

    private var _binding: FragmentMedicalHistoryBinding? = null

    private val binding get() = _binding!!

    val viewModel : ConsultationViewModel by activityViewModels()
   /*val viewModel by lazy {
        ViewModelProvider(this@MedicalHistoryFragment)[ConsultationViewModel::class.java]
    }*/

    val myAdapter by lazy {
        MedicalHistoryAdapter(requireContext())
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

        val viewModel = viewModel
        //getting the position of the patient in the consultation room
        val pPosition = requireActivity().intent.getIntExtra(PATIENT_VISITS, -1)
        //getting he/her medical history


        val patient = viewModel.getCurrentPatientAtConsultation(pPosition)
        //setting all views with his/her details
        bindPatientDetails(patient)
val layoutManager = LinearLayoutManager(requireContext())
        binding.visitsRecycleView.apply {
        viewModel.getAllPatientDiagnoses(pPosition).observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                binding.noMedic.visibility = View.GONE
                binding.noMedicals.visibility = View.GONE
            }
            myAdapter.submitList(it)
            setLayoutManager(layoutManager)
            adapter = myAdapter
        }}
        //Intending to add diagnoses & prescription
        binding.addPatientDiagnosis.setOnClickListener {

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
                patientGender.text = sex
                patientDoB.text = dob
                patientNhis.text = insuranceNumber
                patientMobile.text = mobile


            }

        }
    }

    /*private fun bindVitals(){
        viewModel.getAllPatientDiagnoses().observe(viewLifecycleOwner){
            lifecycleScope.launch { Dispatchers.Default
            myAdapter.submitList(it)
            }
        }
    }*/
}
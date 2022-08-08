package com.oyatech.dch.patient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.databinding.FragmentPatientsBinding
import com.oyatech.dch.patient.data.ParticularsAdapter
import com.oyatech.dch.patient.data.listOfPatientPaticulars
import com.oyatech.dch.recordforms.PatientRegistrationFormActivity

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PatientRecords : Fragment() {

    private var _binding: FragmentPatientsBinding? = null
    val viewModel = RegisterNewPatientViewModel.viewModel
//    private val viewModel : RegisterNewPatientViewModel by activityViewModels()
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!
   /* val viewModel by lazy {
        ViewModelProvider(requireActivity())[RegisterNewPatientViewModel::class.java]
    }*/

val viewM = RegisterNewPatientViewModel.viewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPatientsBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //populating patient data using recycleView
      // viewModel.setParticulars()
        Log.i("Record", "onViewCreated: ${viewModel.getBioData().size}")
        val adapter = ParticularsAdapter(requireContext(), viewModel.getBioData())
        val layoutManager = LinearLayoutManager(requireContext())
        with(binding.patientRecycleView){
            setLayoutManager(layoutManager)
            setAdapter(adapter)

        //    addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }

        binding.addPatient.setOnClickListener{
            startActivity(Intent(context?.applicationContext, PatientRegistrationFormActivity::class.java))
            //clears the fragment from stack
            Toast.makeText(context,"Click", Toast.LENGTH_SHORT).show()
            activity?.finish()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }

}
package com.oyatech.dch.patient

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
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
    private val viewModel : RegisterNewPatientViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPatientsBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    //    val viewModel = ViewModelProvider(requireActivity()).get(RegisterNewPatientViewModel::class.java)
        //populating patient data using recycleView

        val adapter = ParticularsAdapter(requireContext(),viewModel.allPatient)
        with(binding.patientRecycleView){
            layoutManager = LinearLayoutManager(context)
            setAdapter(adapter)

        //    addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }

        viewModel.getPatient().observe(viewLifecycleOwner){
            adapter.submitList(listOfPatientPaticulars)
        }
        /*binding.patientRecycleView.
           binding.patientRecycleView.
           //drawing a divider line between each data set(Patient)
           binding.patientRecycleView.addItemDecoration(
                   DividerItemDecoration(this.requireContext(),DividerItemDecoration.VERTICAL)
                   )*/


        binding.addPatient.setOnClickListener{
            startActivity(Intent(context?.applicationContext, PatientRegistrationFormActivity::class.java))
            //clears the fragment from stack
            Toast.makeText(context,"Click", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
      /*  binding.patientRecycleView.adapter = ParticularsAdapter(requireContext(),
            listOfPatientPaticulars)*/
       /* binding.patientRecycleView.adapter = ParticularsAdapter(viewModel.patientList.sortedBy {
            it.otherNames
        })*/
    }

}
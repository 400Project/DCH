package com.oyatech.dch.patient

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.databinding.FragmentPatientsBinding
import com.oyatech.dch.patient.data.Particulars
import com.oyatech.dch.patient.data.ParticularsAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Patients : Fragment() {

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

        val viewModel = ViewModelProvider(requireActivity()).get(RegisterNewPatientViewModel::class.java)
        //populating patient data using recycleView


        binding.patientRecycleView.adapter = ParticularsAdapter(viewModel.patientList)
           binding.patientRecycleView.layoutManager = LinearLayoutManager(this.requireContext())
           //drawing a divider line between each data set(Patient)
           binding.patientRecycleView.addItemDecoration(
                   DividerItemDecoration(this.requireContext(),DividerItemDecoration.VERTICAL)
                   )





        binding.addPatient.setOnClickListener{
            startActivity(Intent(context,PatientRegistrationFormActivity::class.java))
            Toast.makeText(context,"Click", Toast.LENGTH_SHORT).show()
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.patientRecycleView.adapter = ParticularsAdapter(viewModel.patientList)
       /* binding.patientRecycleView.adapter = ParticularsAdapter(viewModel.patientList.sortedBy {
            it.otherNames
        })*/
    }

}
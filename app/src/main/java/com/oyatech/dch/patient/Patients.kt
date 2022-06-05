package com.oyatech.dch.patient

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.databinding.FragmentPatientsBinding
import com.oyatech.dch.patient.data.ParticularsAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Patients : Fragment() {

    private var _binding: FragmentPatientsBinding? = null
    private val viewModel = RegisterNewPatientViewModel()

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

       binding.patientRecycleView.adapter = ParticularsAdapter(viewModel.patientList.sortedBy {
           it.otherNames
       })
        binding.patientRecycleView.layoutManager = LinearLayoutManager(this.requireContext())

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
        binding.patientRecycleView.adapter = ParticularsAdapter(viewModel.patientList.sortedBy {
            it.otherNames
        })
    }

  }
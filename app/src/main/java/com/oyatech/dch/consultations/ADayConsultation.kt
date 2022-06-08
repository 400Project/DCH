package com.oyatech.dch.consultations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.databinding.FragmentPatientsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.patient.data.Particulars
import com.oyatech.dch.patient.data.ParticularsAdapter


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ADayConsultation : Fragment() {
private val viewModel : RegisterNewPatientViewModel by viewModels()
    private var _binding: FragmentPatientsBinding? = null

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

        binding.patientRecycleView.adapter = ParticularsAdapter(viewModel.patientList)
        binding.patientRecycleView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.patientRecycleView.adapter?.notifyDataSetChanged()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.patientRecycleView.adapter = ParticularsAdapter(viewModel.patientList)
    }

}
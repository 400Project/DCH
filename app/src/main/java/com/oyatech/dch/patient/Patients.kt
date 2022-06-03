package com.oyatech.dch.patient

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.RegisterNewPatient
import com.oyatech.dch.databinding.FragmentPatientsBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Patients : Fragment() {

    private var _binding: FragmentPatientsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPatientsBinding.inflate(inflater, container, false)
        binding.patientRecord.text = "This is Patient's Database"
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addPatient.setOnClickListener{
            /**
             * TODO: USE navGraph to navigate to the registration form from Patient tab
             */
            Toast.makeText(context,"Click", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.oyatech.dch.vitals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.auth.User
import com.oyatech.dch.databinding.FragmentPatientVitalsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.patient.data.listOfPatientPaticulars


class PatientVitalsFragment : Fragment() {
private  var _binding :FragmentPatientVitalsBinding?=null
    private val binding get() = _binding!!


  private  val viewModel : RegisterNewPatientViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPatientVitalsBinding.inflate(inflater,container,false)
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
  // val     viewModel = ViewModelProvider(requireActivity()).get(RegisterNewPatientViewModel::class.java)


        val adapter = VitalsAdapter(requireContext(),viewModel.allPatient)
        with(binding.vitalsRecycleView){
            layoutManager = LinearLayoutManager(requireContext())
            setAdapter(adapter)
        }




    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
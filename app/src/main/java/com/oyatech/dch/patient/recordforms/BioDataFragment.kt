package com.oyatech.dch.patient.recordforms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentBioDataBinding


class BioDataFragment : Fragment() {

 private   var _binding:  FragmentBioDataBinding? = null

   private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          _binding = FragmentBioDataBinding.inflate(LayoutInflater.from(context)
            ,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.vitalsFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
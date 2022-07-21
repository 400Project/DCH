package com.oyatech.dch.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentDignosesBinding
import com.oyatech.dch.databinding.FragmentVitalsBinding


class VitalsFragment : Fragment() {
    private var _binding : FragmentVitalsBinding?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVitalsBinding.
        inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      /*  binding.vital.setOnClickListener{
            findNavController().navigate(R.id.dignosesFragment)
        }*/
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}
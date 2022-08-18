package com.oyatech.dch.recordforms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oyatech.dch.databinding.FragmentNextOfKingBinding


/**
 * A simple [Fragment] subclass.
 * Use the [PatientNextOfFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PatientNextOfFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding:FragmentNextOfKingBinding?=null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNextOfKingBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
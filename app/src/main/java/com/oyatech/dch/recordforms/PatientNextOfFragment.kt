package com.oyatech.dch.recordforms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.oyatech.dch.patient.PatientBioViewModel
import com.oyatech.dch.databinding.FragmentNextOfKingBinding


/**
 * A simple [Fragment] subclass.
 * Use the [PatientNextOfFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PatientNextOfFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding:FragmentNextOfKingBinding?=null
private var primaryKey = 0
    private val binding get() = _binding!!

    val viewModel by lazy {
        ViewModelProvider(this@PatientNextOfFragment)[PatientBioViewModel::class.java]
    }
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

        val viewModel = viewModel
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun getNextOfKingDetails()
    {
       /* with(binding) {
            var nxtKFullName = nextOfKingName.text.toString().trim()
            var
            val vitals = NextOfKin(primaryKey,"4/12/2022",bloo)
        }*/
    }

}
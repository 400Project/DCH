package com.oyatech.dch.vitals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.databinding.FragmentPatientVitalsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel


class PatientVitalsFragment : Fragment() {
private  var _binding :FragmentPatientVitalsBinding?=null
    private val binding get() = _binding!!


  private  val viewModel by lazy {
      ViewModelProvider(requireActivity())[VitalsViewModel::class.java]
  }

    private val myAdapter by lazy {
        VitalsAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPatientVitalsBinding.inflate(inflater,container,false)
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = myAdapter
        val viewModel = viewModel

        viewModel.queuedForVitals.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
       binding.vitalsRecycleView.apply{
            layoutManager = LinearLayoutManager(requireContext())
            setAdapter(adapter)
        }


        binding.clear.setOnClickListener{
            viewModel.clearVitalsList()
            binding.vitalsRecycleView.adapter?.notifyDataSetChanged()
        }

        Log.i("Vitals", "onViewCreated: is called")
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onResume() {
        Log.i("Vitals", "onResume: is called")
        super.onResume()

    }

    override fun onPause() {
        Log.i("Vitals", "onPause: is called")
        super.onPause()
        binding.vitalsRecycleView.adapter = myAdapter
    } }
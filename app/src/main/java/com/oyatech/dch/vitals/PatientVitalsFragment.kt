package com.oyatech.dch.vitals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.PatientBioViewModel
import com.oyatech.dch.databinding.FragmentPatientVitalsBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PatientVitalsFragment : Fragment() {
private  var _binding :FragmentPatientVitalsBinding?=null
    private val binding get() = _binding!!
    private val v = mutableListOf<PatientBioData>()

  private  val viewModel by lazy {
      ViewModelProvider(this@PatientVitalsFragment)[VitalsViewModel::class.java]
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

        viewModel.patientAndVitals().observe(viewLifecycleOwner){ it ->
            val  v = mutableListOf<PatientBioData>()
            lifecycleScope.launch { Dispatchers.IO
                it.forEach {
                    v.add(it.patientBioData)
                }
            }

            adapter.submitList(v)

        }



       binding.vitalsRecycleView.apply{
            layoutManager = LinearLayoutManager(requireContext())
            setAdapter(adapter)
        }


        binding.clear.setOnClickListener{
    //        viewModel.clearVitalsList()
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
package com.oyatech.dch.consultations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.databinding.FragmentConsultationBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ConsultationsFragment : Fragment() {

    private val consultAdapter by lazy {
        ConsultationAdapter(requireContext())
    }
    companion object{
        val trees = TreeMap<Int , PatientBioData> ()
    }

    private val viewModel: ConsultationViewModel by activityViewModels()
    private var _binding: FragmentConsultationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConsultationBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecyclerView()
    }

    private fun createRecyclerView() {
        val adapter = consultAdapter
        binding.consultReviewer.apply {

            layoutManager = LinearLayoutManager(requireContext())

        viewModel.fetchDailyConsultRemote().observe(viewLifecycleOwner) { it ->
            val v = mutableListOf<PatientBioData>()
            lifecycleScope.launch {
                Dispatchers.IO
                it.forEach {
                    v.add(it.bios)
                    trees[it.bios.patientId] = it.bios
                }
            }
            adapter.submitList(v)

            if (v.isEmpty()){
                binding.apply {
                    noVitals.visibility = View.VISIBLE
                    noMedic.visibility = View.VISIBLE
                }
            }
        }
            setAdapter(adapter)
        }
    }

    override fun onResume() {

      createRecyclerView()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        Log.i("Consultation", "onDestroyView: $viewModel ")

    }

}
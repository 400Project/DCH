package com.oyatech.dch.consultations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.databinding.FragmentConsultationBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ConsultationsFragment : Fragment() {

    private val consultAdapter by lazy {
        ConsultationAdapter(requireContext())
    }
    private val viewModel by lazy {
        //correct syntax to create a ViewModel that lives until the fragment goes away permanently

        ViewModelProvider(this@ConsultationsFragment)[ConsultationViewModel::class.java]
    }
    private var _binding: FragmentConsultationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var v = mutableListOf<PatientBioData>()

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
        val viewModel = viewModel

        viewModel.getAllBookedForConsultation().observe(viewLifecycleOwner) { it ->

            lifecycleScope.launch {
                Dispatchers.IO
                it.forEach {
                    v.add(it.bios)
                }
                adapter.submitList(v)
            }


        }
        binding.consultReviewer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setAdapter(adapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}
package com.oyatech.dch.consultations

import android.icu.lang.UCharacter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.databinding.FragmentConsultationBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ConsultationsFragment : Fragment() {

    private val consultAdapter by lazy {
        ConsultationAdapter(requireContext())
    }
    private val viewModel by lazy {
        //correct syntax to create a ViewModel that lives until the fragment goes away permanently

        /*
        *TODO: communication and data sharing between Fragments and Activities in an Android application IS Shared ViewModel
        * */
        ViewModelProvider(this@ConsultationsFragment)[ConsultationViewModel::class.java]
    }
    private var _binding:FragmentConsultationBinding? = null

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
        val viewModel = viewModel
        viewModel.queuedForConsultation.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
     binding.consultReviewer.apply{
        layoutManager =   LinearLayoutManager(requireContext())
               setAdapter(adapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
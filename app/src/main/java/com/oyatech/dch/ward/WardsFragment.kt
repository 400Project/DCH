package com.oyatech.dch.ward

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.consultations.ConsultationAdapter
import com.oyatech.dch.IRecyclatesCreator
import com.oyatech.dch.databinding.FragmentWardsBinding


class WardsFragment : Fragment(),IRecyclatesCreator {
    private var _binding : FragmentWardsBinding?= null
    private val binding get() = _binding!!

    private val wardAdapter by lazy {
        WardAdapter(requireContext())
    }
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[WarViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWardsBinding.
        inflate(inflater,container,false)
        return _binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       recycleFactory()
    }

    private fun createRecyclerView() {
        val adapter = wardAdapter
        val viewModel = viewModel
        viewModel.patientsInWard.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.wardReviewer.apply{
            layoutManager =   LinearLayoutManager(requireContext())
            setAdapter(adapter)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }

    override fun recycleFactory() {
        val adapter = wardAdapter
        val viewModel = viewModel
        viewModel.patientsInWard.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.wardReviewer.apply{
            layoutManager =   LinearLayoutManager(requireContext())
            setAdapter(adapter)
        }
    }
}
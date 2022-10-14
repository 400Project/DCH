package com.oyatech.dch.ward

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.IRecyclatesCreator
import com.oyatech.dch.databinding.FragmentWardsBinding


class WardsFragment : Fragment(), IRecyclatesCreator {
    private var _binding: FragmentWardsBinding? = null
    private val binding get() = _binding!!

    private val wardAdapter by lazy {
        WardAdapter(requireContext())
    }
    private val viewModel by lazy {
        ViewModelProvider(this@WardsFragment)[WardViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWardsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = wardAdapter
        val myWardVieModel = viewModel

        binding.wardReviewer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            myWardVieModel.fetchWard().observe(viewLifecycleOwner) {
                wardAdapter.submitList(it)

                if(it.isEmpty()){
                    binding.emptyWard.visibility = View.VISIBLE
                }
                setAdapter(wardAdapter)
            }
        }
    }


    // TODO: Make a network call for patient bio data at Medical History and Ward
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun recycleFactory() {

        binding.wardReviewer.apply {
            viewModel.fetchWard().observe(viewLifecycleOwner) {
                wardAdapter.submitList(it)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = wardAdapter
            }
        }
    }
}
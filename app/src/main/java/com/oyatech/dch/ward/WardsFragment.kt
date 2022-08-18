package com.oyatech.dch.ward

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oyatech.dch.databinding.FragmentWardsBinding


class WardsFragment : Fragment() {
    private var _binding : FragmentWardsBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWardsBinding.
        inflate(inflater,container,false)
        return _binding!!.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}
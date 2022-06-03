package com.oyatech.dch

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.oyatech.dch.databinding.FragmentRegisterNewPatientBinding

class RegisterNewPatient : Fragment() {

    companion object {
        fun newInstance() = RegisterNewPatient()
    }

    private lateinit var viewModel: RegisterNewPatientViewModel
    private lateinit var registerBinding: FragmentRegisterNewPatientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBinding = FragmentRegisterNewPatientBinding.inflate(inflater
        ,container,false)
        return registerBinding.root
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterNewPatientViewModel::class.java)
    }


}
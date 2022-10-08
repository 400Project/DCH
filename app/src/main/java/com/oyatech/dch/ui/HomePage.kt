package com.oyatech.dch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oyatech.dch.R
import com.oyatech.dch.alerts.call
import com.oyatech.dch.alerts.location

import com.oyatech.dch.alerts.toaster

import com.oyatech.dch.alerts.whatsApp
import com.oyatech.dch.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomePage : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Calling onClick on the views
        binding.footer.apply {
            with(this@HomePage) {
                whatApp.setOnClickListener(this)
                call.setOnClickListener(this)
                location.setOnClickListener(this)
            }

        }
        binding.footer.whatApp.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onClick(p0: View?) {
        binding.footer.apply {
            when (p0) {
                //id
                whatApp -> {
                    //whatsApp intent method written as context extension
                    requireContext().apply {
                        whatsApp()


           toaster(getString(R.string.whatsapp_chat))

                    }
                }
                call -> {
                    requireContext().apply {
                        call()


        toaster(getString(R.string.calling))
                   }
                }

                location -> {
                    requireContext().apply {
                        location()

       toaster(getString(R.string.location))

                    }
                }
            }

        }

    }
}
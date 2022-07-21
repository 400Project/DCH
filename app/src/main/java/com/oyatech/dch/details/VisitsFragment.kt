package com.oyatech.dch.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentDetailRecordBinding
import com.oyatech.dch.databinding.FragmentVisitsBinding
import com.oyatech.dch.databinding.VisitsCardBinding

/**
 * A simple [Fragment] subclass.
 * Use the [VisitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisitsFragment : Fragment() {

    private var _binding :VisitsCardBinding? =null

    private val binding get()= _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = VisitsCardBinding.inflate(inflater,container,false)


        return binding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.doctorName.setOnClickListener {
            findNavController().navigate(R.id.detailRecordFragment)
        }

        binding.visitDate.setOnClickListener {
            findNavController().navigate(R.id.dignosesFragment)
        }
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after [.onStop] and before [.onDetach].
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
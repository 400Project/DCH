package com.oyatech.dch.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentDetailRecordBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailRecordFragment : Fragment() {

     var _binding : FragmentDetailRecordBinding? = null
    val binding get() = _binding!!


    private val isVitals = 0
    private val isDiagnose = 1
  private val isPres = 2


    private val showMore = arrayListOf(false,false,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDetailRecordBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.patientVitalsLayout.isVisible = false
        binding.patientDiagnosisLayout.isVisible = false
        binding.patientPrescriptionLayout.isVisible = false

        with(binding){
            vitalsLayoutController.setOnClickListener {
                lessMoreDetailsLayout(patientVitalsLayout,vitalsLayoutController, showMore,isVitals)
            }

            diagnosisLayoutController.setOnClickListener {
                lessMoreDetailsLayout(patientDiagnosisLayout,diagnosisLayoutController,showMore,isDiagnose)
            }
            prescriptionLayoutController.setOnClickListener {
                lessMoreDetailsLayout(patientPrescriptionLayout,prescriptionLayoutController,showMore,isPres)
            }
        }

        binding.patientBioLayout.setOnClickListener{
            findNavController().navigate(R.id.detailRecordFragment)
        }

        binding.patientDiagnosisLayout.setOnClickListener{
            findNavController().navigate(R.id.dignosesFragment)
        }
        binding.patientPrescriptionLayout.setOnClickListener{
            findNavController().navigate(R.id.prescriptionFragment)
        }

    }


    private fun lessMoreDetailsLayout(view:View,
                                      textView: TextView,
                                      isMore: ArrayList<Boolean>,
                                      ps:Int){

        if (!isMore.get(ps)){
            view.isVisible = true
            textView.text = "Less Details..."
            isMore[ps] = true

        }else{
            view.isVisible = false
            textView.text = "More Details..."
            isMore[ps] = false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
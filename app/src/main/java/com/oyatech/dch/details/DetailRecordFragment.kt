package com.oyatech.dch.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.consultations.ConsultationViewModel
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.Vitals
import com.oyatech.dch.databinding.FragmentDetailRecordBinding
import com.oyatech.dch.patient.PatientBioViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [DetailRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailRecordFragment : Fragment() {

     var _binding : FragmentDetailRecordBinding? = null
    val binding get() = _binding!!

    val viewModel : ConsultationViewModel by activityViewModels()
    val patientViewModel : PatientBioViewModel by activityViewModels()


    private val showMore = arrayListOf(false,false,false)

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

    val id = arguments?.getInt("diagnoses")

      val vitals =  viewModel.getCurrentVitals(id!!)

        bindDataToViews(vitals)

    //    bindToDiagnose(viewModel.diagnose)
        binding.patientDiagnosisLayout.setOnClickListener{
       Toast.makeText(context, "Click",Toast.LENGTH_SHORT).show()
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

 private  fun bindDataToViews(vitals:Vitals){
        binding.apply {
            with(vitals){
                patientTemperature.text =bodyTemperature
                patientWeight.text = weight
                patientBloodPressure.text = bloodPressure
                patientSugarLevel.text = sugarLevel
                recordedBy.text = recordBy
            }

        }
    }

    private fun bindToDiagnose(diagnose:Diagnose){
        binding.apply {
            with(diagnose){
                provisionalDiagnosis.text = provisional
                principalDiagnose.text = principal
                additionalDiagnosis.text = additional
                dateDiagnose.text = date

            }
        }
    }
}
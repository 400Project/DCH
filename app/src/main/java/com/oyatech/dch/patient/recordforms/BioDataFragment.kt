package com.oyatech.dch.patient.recordforms

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.oyatech.dch.databinding.FragmentBioDataBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.datacenter.PatientsDataPage
import java.util.*


class BioDataFragment : Fragment() {

 private   var _binding:  FragmentBioDataBinding? = null

   private val binding get() = _binding!!

   val viewModel = RegisterNewPatientViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          _binding = FragmentBioDataBinding.inflate(LayoutInflater.from(context)
            ,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Phone number formatter according to Ghana local
       formatPhoneNumber()


        binding.register.setOnClickListener {
            val firstName = binding.patientFirstName.text.toString().trim()
            val otherName = binding.patientOtherNames.text.toString().trim()
       //     val patient = Particulars(firstName,otherName,viewModel.getDateAndTime())
          //  viewModel.getConsultationQue(patient)
            startActivity(Intent(this.context,PatientsDataPage::class.java))
            activity?.finish()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun formatPhoneNumber(){
        with(binding.patientMobile){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                this.addTextChangedListener(PhoneNumberFormattingTextWatcher(Locale.getDefault().country))
            }else{

                this.addTextChangedListener{
                    text: Editable? -> text?.replace(0,text.length,PhoneNumberUtils.formatNumber(text.toString()))
                }
            }
        }

    }
}
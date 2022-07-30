package com.oyatech.dch.recordforms

import android.app.DatePickerDialog
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
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentBioDataBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.datacenter.PatientsDataPage
import kotlinx.coroutines.processNextEventInCurrentThread
import java.time.Month
import java.util.*


class BioDataFragment : Fragment() {
//View Binding
 private   var _binding:  FragmentBioDataBinding? = null

   private val binding get() = _binding!!


   private val calender: Calendar = Calendar.getInstance()
   private var patientYear:Int = 0

    private val viewModel : RegisterNewPatientViewModel by activityViewModels()

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

        binding.patientDoBLayout.setOnClickListener{
            //getting the calender by using the DatePickerDialog
       pickDate()

            //Computing the current age of the patient using the different in the current year and
           // the year of the his dob
            var currentYear = calender.get(Calendar.YEAR)
            var age = currentYear - patientYear
            binding.patientAge.text = age.toString()+"yrs"
        }
binding.next.setOnClickListener {
    findNavController().navigate(R.id.action_bioDataFragment_to_nextOfFragment)
}

        binding.done.setOnClickListener {
            Toast.makeText(requireContext(),"Patient Added",Toast.LENGTH_SHORT).show()
            val firstName = binding.patientFirstName.text.toString().trim()
            val otherName = binding.patientOtherNames.text.toString().trim()
       //     val patient = Particulars(firstName,otherName,viewModel.getDateAndTime())
          //  viewModel.getConsultationQue(patient)
            startActivity(Intent(this.context,PatientsDataPage::class.java))
            activity?.finish()
        }
    }
//Getting the date from the date picker
    private fun pickDate() {

        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
    val  picker =  DatePickerDialog(requireContext(),
        { datePicker, year, month, day ->
            val monthOfBirth = month +1
            patientYear = year
          binding.patientDoBLayout.setText("$day/$monthOfBirth/$year")
            binding.patientAge.text = (calender.get(Calendar.YEAR)-year).toString()+"yrs"
      },year,month,day)
        picker.show()
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
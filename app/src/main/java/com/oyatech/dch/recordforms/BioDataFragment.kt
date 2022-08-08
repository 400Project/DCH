package com.oyatech.dch.recordforms

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentBioDataBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.datacenter.PatientsDataPage
import com.oyatech.dch.patient.data.Particulars
import kotlinx.coroutines.processNextEventInCurrentThread
import java.time.Month
import java.util.*


class BioDataFragment : Fragment() {
//View Binding
 private   var _binding:  FragmentBioDataBinding? = null
val viewModel = RegisterNewPatientViewModel.viewModel
   private val binding get() = _binding!!


   private val calender: Calendar = Calendar.getInstance()
   private var patientYear:Int = 0
   lateinit var sex: String
   companion object{
       private  var hospitalNumber:Int = 0
   }


  /* val viewModel by lazy {
       ViewModelProvider(requireActivity())[RegisterNewPatientViewModel::class.java]
   }*/
 //   private val viewModel : RegisterNewPatientViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          _binding = FragmentBioDataBinding.inflate(LayoutInflater.from(context)
            ,container,false)
        return binding.root.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    //    viewModel = RegisterNewPatientViewModel().getViewModel()
        //Phone number formatter according to Ghana local
       formatPhoneNumber()

        binding.patientDoB.setOnClickListener{
            //getting the calender by using the DatePickerDialog
       pickDate()

            //Computing the current age of the patient using the different in the current year and
           // the year of the his dob
            val currentYear = calender.get(Calendar.YEAR)
            val age = currentYear - patientYear
            if (age>100){
                binding.patientDoB.setError("Selec Date of Birth")
                binding.patientAge.text = " yrs"
            }else
            binding.patientAge.text = age.toString()+"yrs"
        }

        binding.patientSex.onItemSelectedListener = getPatientSex()

        Toast.makeText(context,"Spinner",Toast.LENGTH_SHORT).show()
        //Navigate to the next of kin fragment
binding.next.setOnClickListener {
    findNavController().navigate(R.id.action_bioDataFragment_to_nextOfFragment)
}

        //register new patient to the hospital records database
        binding.done.setOnClickListener {
            addNewPatient()

        }
    }

    private fun getPatientSex():AdapterView.OnItemSelectedListener {
//The createFromResource() method allows you to create an ArrayAdapter from the string array.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sex,
            android.R.layout.simple_spinner_item
            /**
             * You should then call setDropDownViewResource(int) to specify the layout the adapter
             * should use to display the list of spinner choice
             */
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            binding.patientSex.adapter = arrayAdapter
//Responding to user selection
        val item: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapteView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    sex = adapteView?.getItemAtPosition(position).toString()
                    Log.i("Sinner", "onItemSelected: ${adapteView?.getItemAtPosition(position)}")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            return item

        }
    }

    //Creating new particulars object
    private fun addNewPatient() {
        with(binding){
            Toast.makeText(requireContext(),"Patient Added",Toast.LENGTH_SHORT).show()
            val date = viewModel.getDateAndTime()
           val sex = sex
            val hospitalNumber = generateHospitalNumber()
            val firstName = patientFirstName.text.toString().trim()
            val otherName = patientOtherNames.text.toString().trim()
            val address =patientAddress.text.toString().trim()
            val dob =patientDoB.text.toString().trim()
            val age = computeDateOfBirth(calender.get(Calendar.YEAR),patientYear)
            val occupation=patientOccupation.text.toString().trim()
            val mobile =patientMobile.text.toString().trim()
            val nhis =patientNhis.text.toString().trim()


                val particulars = Particulars(hospitalNumber,
                    firstName, otherName,
                    address,dob,sex,
                    occupation,date,
                    mobile,nhis,age)
               viewModel.setBioData(particulars)
            viewModel.setQueuedForVitals(particulars)

            /*, address, dob, sex, occupation, date*/
        }
        startActivity(Intent(this.context,PatientsDataPage::class.java))
        activity?.finish()
    }

    //Getting the date from the date picker
    private fun pickDate() {

        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
     DatePickerDialog(requireContext(),
        { datePicker, year, month, day ->
            val monthOfBirth = month +1
            patientYear = year
          binding.patientDoB.setText("$day/$monthOfBirth/$year")
            if (year<1910){
                binding.patientDoB.error = "Select DOB"
            }else
            binding.patientAge.text = (calender.get(Calendar.YEAR)-year).toString()+"yrs"
      },year,month,day).show()

    }

    private fun computeDateOfBirth(yearOfBirth:Int,currentYear:Int):String{
        val age = currentYear - yearOfBirth
        return "$age yrs"
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


    private fun generateHospitalNumber ():String{
        val year = calender.get(Calendar.YEAR)
        hospitalNumber += 1

        return  "DCH/$hospitalNumber/$year"
    }

}
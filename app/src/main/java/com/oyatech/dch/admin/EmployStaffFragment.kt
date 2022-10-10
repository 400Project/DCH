package com.oyatech.dch.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.alerts.isEmptyView
import com.oyatech.dch.databinding.FragmentEmployStaffBinding
import com.oyatech.dch.model.Staff

class EmployStaffFragment : Fragment() {
    // TODO: Rename and change types of parameters
    val viewModel:StaffViewModel by activityViewModels()
    private   var _binding: FragmentEmployStaffBinding? =null
    private   val binding get() = _binding!!

    private var staff_department:String = ""
    private var staff_genders:String= ""
    private var dob:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmployStaffBinding.inflate(
            inflater,
            container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {
            department.onItemSelectedListener = getStaffDepartment()
            staffGender.onItemSelectedListener = getStaffGender()
            staffDoB.setOnClickListener{
         //       requireContext().pickDate()
            }

            recruit.setOnClickListener{
                if (!newStaff()){

                    Toast.makeText(requireContext(),"Staff Recruit",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_recruit_staff_to_staffing)
                }else
                    Toast.makeText(requireContext(),"Network Error",Toast.LENGTH_SHORT).show()

            }
        }
        /**
         * TODO: save staff and populate then login to the appropriate departments
         */
    }

    private fun newStaff():Boolean{
        binding.apply {
            val firstName = trimText(staffFirstName)
            val otherName = trimText(staffOtherNames)
            val address = trimText(staffAddress)
            val dob = dob
            val gender = staff_genders
            var department = staff_department
            val staff_Role = trimText(staffRole)
            val mobile = trimText(staffMobile)
            val email = trimText(staffEmail)
            val insurance = trimText(staffInsurance)

            return if ( (isEmptyView(staffFirstName))||
                (isEmptyView(staffOtherNames))||
                (isEmptyView(staffAddress))||
                (isEmptyView(staffRole))||
                (isEmptyView(staffMobile))||
                (isEmptyView(staffEmail))){
                true
            }else{
                val staff = Staff(firstName,otherName,address,
                dob,gender,department,staff_Role,email,mobile,insurance)
                viewModel.addStaff(staff)
                Toast.makeText(requireContext(),"$firstName Added", Toast.LENGTH_SHORT).show()
                false
            }
        }


    }

    private fun trimText(editText:EditText):String{
        return editText.text.toString().trim()
    }

    private fun getStaffGender(): AdapterView.OnItemSelectedListener {
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
            binding.staffGender.adapter = arrayAdapter
//Responding to user selection
            val item: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapteView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    staff_genders = adapteView?.getItemAtPosition(position).toString()
                    Log.i("Sinner", "onItemSelected: ${adapteView?.getItemAtPosition(position)}")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            return item

        }
    }

    private fun getStaffDepartment():AdapterView.OnItemSelectedListener {
//The createFromResource() method allows you to create an ArrayAdapter from the string array.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.department,
            android.R.layout.simple_spinner_item
            /**
             * You should then call setDropDownViewResource(int) to specify the layout the adapter
             * should use to display the list of spinner choice
             */
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            binding.department.adapter = arrayAdapter
//Responding to user selection
            val item: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapteView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    staff_department = adapteView?.getItemAtPosition(position).toString()
                    Log.i("Sinner", "onItemSelected: ${adapteView?.getItemAtPosition(position)}")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            return item

        }
    }

}
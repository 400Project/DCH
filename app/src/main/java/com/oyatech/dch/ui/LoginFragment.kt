package com.oyatech.dch.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.oyatech.dch.R
import com.oyatech.dch.admin.AdminActivity
import com.oyatech.dch.admin.StaffViewModel
import com.oyatech.dch.alerts.isEmptyView
import com.oyatech.dch.databinding.FragmentLoginBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LoginFragment : Fragment() {
    val TAG = LoginFragment::class.java.simpleName
    private var staff_department: String = ""
    private var _binding: FragmentLoginBinding? = null
    private val viewMode: StaffViewModel by activityViewModels()
    private val DEPARTMENT = "department"
    private var gender = ""
    var department = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to [Activity.onStart] of the containing
     * Activity's lifecycle.
     */
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(requireContext(), "Signed In", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(requireContext(), "No Account yet", Toast.LENGTH_SHORT).show()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
//Setting auto complete for users
        //    autocomplete()

   //     binding.department.onItemSelectedListener = getStaffDepartment()

        binding.login.setOnClickListener {
            if (staff_department == "Admin") {
                startActivity(Intent(requireContext(), AdminActivity::class.java))
            } else {


                val email = binding.staffId.text.toString().trim()
                val password = binding.staffPassword.text.toString().trim()
               if (isEmptyView(binding.staffId)||isEmptyView(binding.staffPassword)){

               }else if (!email.contains("@")) {
                    binding.staffId.error = "Missing @ or .com"

                } else {
                    viewMode.getStaff(password).observe(viewLifecycleOwner) { staff ->
                        val staff = staff
                        if (staff == null) {
                            Toast.makeText(
                                requireContext(),
                                "User those not exist",
                                Toast.LENGTH_LONG * 3
                            ).show()
                        } else {
             signIn(email, password)

                        }
                    }

                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Setting AutoComplete. this will be implemented in the description field
    /* private fun autocomplete(){
         val stringAuto = resources.getStringArray(R.array.autocomplete_address)
         val adapterView = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,stringAuto)
         binding.staffId.setAdapter(adapterView)
     }*/


    private fun signIn(email: String, password: String): Boolean {
        var success = false
        binding.apply {
            progressBar.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Sign In:${result.result.user?.email}",
                            Toast.LENGTH_SHORT
                        ).show()
                        //launching an activity
                        staff_department = password.substring(4, 7)
                        val intent =
                            Intent(requireContext(), PatientsDataPageActivity::class.java)
                        intent.putExtra(DEPARTMENT, staff_department)

                        startActivity(intent)
                    }
                }.addOnFailureListener { failure ->
                    progressBar.visibility = View.INVISIBLE
                    noUser.visibility = View.VISIBLE
                    noUser.text = "Incorrect Credential"
                    Toast.makeText(
                        requireContext(),
                        "LogIn failed:${failure.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            return success
        }
    }

    private fun getPatientSex(): AdapterView.OnItemSelectedListener {
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
            val item: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapteView: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        p3: Long
                    ) {
                        department = adapteView?.getItemAtPosition(position).toString()
                        Log.i(
                            "Sinner",
                            "onItemSelected: ${adapteView?.getItemAtPosition(position)}"
                        )
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }
            return item

        }
    }

    private fun getStaffDepartment(): AdapterView.OnItemSelectedListener {
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
            val item: AdapterView.OnItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapteView: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        p3: Long
                    ) {
                        staff_department = adapteView?.getItemAtPosition(position).toString()
                        Log.i(
                            "Sinner",
                            "onItemSelected: ${adapteView?.getItemAtPosition(position)}"
                        )
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }
            return item

        }
    }
}



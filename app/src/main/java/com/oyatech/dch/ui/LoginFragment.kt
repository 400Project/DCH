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
import com.oyatech.dch.DepartmentPreference
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
    private val dpPreference by lazy {
        DepartmentPreference(requireContext())
    }
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
  /*  override fun onStart() {
        super.onStart()
        val preference = dpPreference
        staff_department = preference.department
        val currentUser = auth.currentUser
        if ((staff_department.isNotEmpty()) && (currentUser != null)) {
            intentToDataCenter(staff_department)
            Toast.makeText(requireContext(), "Signed In", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(requireContext(), "No Account yet", Toast.LENGTH_SHORT).show()

    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
//Setting auto complete for users
        //    autocomplete()


        //     binding.department.onItemSelectedListener = getStaffDepartment()

        binding.login.setOnClickListener {

                val email = binding.staffId.text.toString().trim()
                val password = binding.staffPassword.text.toString().trim()
                if (isEmptyView(binding.staffId) || isEmptyView(binding.staffPassword)) {

                } else if (!email.contains("@")) {
                    binding.staffId.error = "Missing @ or .com"

                } else
                    signIn(email, password)
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


    private fun signIn(email: String, password: String) {

        binding.apply {
            progressBar.visibility = View.VISIBLE
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "Sign In:${result.result.user?.email}",
                                Toast.LENGTH_SHORT
                            ).show()
                            //getting the three characters of the passwaord
                            staff_department = password.substring(4, 7)
                            //saving the department to preference
                            dpPreference.saveDepartment(staff_department)
                            //Intent to the right activity
                            intentToDataCenter(staff_department)

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
            }catch (e:Exception){
                binding.noUser.text = e.toString()
            }

        }
    }

    private fun intentToDataCenter(staff_department: String) {
        if (staff_department == "Adm"){
            startActivity(Intent(requireContext(), AdminActivity::class.java))
        }else {
        val intent =
            Intent(requireContext(), PatientsDataPageActivity::class.java)

        startActivity(intent)
    }}
}



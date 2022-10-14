package com.oyatech.dch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.oyatech.dch.DepartmentPreference
import com.oyatech.dch.R
import com.oyatech.dch.admin.AdminActivity
import com.oyatech.dch.admin.StaffViewModel
import com.oyatech.dch.alerts.isEmptyView
import com.oyatech.dch.alerts.snackForError
import com.oyatech.dch.alerts.toaster
import com.oyatech.dch.alerts.trimText
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
//Setting auto complete for users
        //    autocomplete()


        //     binding.department.onItemSelectedListener = getStaffDepartment()

        binding.login.setOnClickListener {

            val email = trimText(binding.staffId)
            val password = trimText(binding.staffPassword)
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
                            //Log in feedback

                            requireContext().toaster("Signed In Successfully")
                            progressBar.visibility = View.INVISIBLE
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
                        noUser.text = getString(R.string.incorrect_credentials)

                        requireContext().snackForError(
                            requireView(),
                            "LogIn failed. Please Check your internet connection"
                        )

                    }
            } catch (e: Exception) {
                binding.noUser.text = e.toString()
            }

        }
    }

    private fun intentToDataCenter(staff_department: String) {
        if (staff_department == "Adm") {
            startActivity(Intent(requireContext(), AdminActivity::class.java))
        } else {
            val intent =
                Intent(requireContext(), PatientsDataPageActivity::class.java)

            startActivity(intent)
            requireActivity().finish()
        }
    }
}



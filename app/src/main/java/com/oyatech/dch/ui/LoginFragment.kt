package com.oyatech.dch.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentLoginBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//Setting auto complete for users
        autocomplete()

       binding.login.setOnClickListener {
            startActivity(Intent(context, PatientsDataPageActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Setting AutoComplete. this will be implemented in the description field
    private fun autocomplete(){
        val stringAuto = resources.getStringArray(R.array.autocomplete_address)
        val adapterView = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,stringAuto)
        binding.userId.setAdapter(adapterView)
    }
}
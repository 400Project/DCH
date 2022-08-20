package com.oyatech.dch.patient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.databinding.FragmentPatientsBinding
import com.oyatech.dch.recordforms.PatientRegistrationFormActivity

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PatientBioFragment : Fragment(){

    private var _binding: FragmentPatientsBinding? = null
   // val viewModel = RegisterNewPatientViewModel.viewModel

//    private val viewModel : RegisterNewPatientViewModel by activityViewModels()
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!

    private val myAdapter by lazy {
        BioDataAdapter(requireContext())
    }
    val viewModel by lazy {
        ViewModelProvider(requireActivity())[RegisterNewPatientViewModel::class.java]
    }

val viewM = RegisterNewPatientViewModel.viewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//TODO: I HAVE TO WORK ON THE SEARCH AGAIN
        _binding = FragmentPatientsBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //populating patient data using recycleView
      // viewModel.setPatientBioData()
        viewModel.dataInitializer()
        val searchView = binding.search
        Log.i("Record", "onViewCreated: ${viewModel.getBioData().size}")


 val myAdapter = myAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.patientRecycleView.apply {
            setLayoutManager(layoutManager)
            viewModel.bioList.observe(viewLifecycleOwner){ bioData ->

               myAdapter.submitList(bioData)
                adapter= myAdapter
        }}
        //    addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))




        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
              return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
               searching(query!!)
                return true
            }

        })



        binding.addPatient.setOnClickListener{
            startActivity(Intent(context?.applicationContext, PatientRegistrationFormActivity::class.java))
            //clears the fragment from stack
            Toast.makeText(context,"Click", Toast.LENGTH_SHORT).show()
            activity?.finish()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }


    fun searching(query:String){
      viewModel.searchForPatient(query).observe(viewLifecycleOwner){ bioData ->
            bioData.let { myAdapter.submitList(it)
          }
        }
    }
    }
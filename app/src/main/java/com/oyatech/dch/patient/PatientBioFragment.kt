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
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.databinding.FragmentPatientsBinding
import com.oyatech.dch.recordforms.PatientRegistrationFormActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PatientBioFragment : Fragment(){

    private val PRIMARY_KEY: String = "patient_primary_key"
    private var _binding: FragmentPatientsBinding? = null
   // val viewModel = RegisterNewPatientViewModel.viewModel
   var primaryKey = 0;
//    private val viewModel : RegisterNewPatientViewModel by activityViewModels()
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!
companion object{
    val tree: TreeMap<Int,PatientBioData> = TreeMap()
}
    private val myAdapter by lazy {
        BioDataAdapter(requireContext())
    }
    val viewModel by lazy {
        ViewModelProvider(this@PatientBioFragment)[PatientBioViewModel::class.java]
    }
    
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
        val searchView = binding.search
        Log.i("Record", "onViewCreated: ${viewModel.getAllBioData().value?.size}")


 val myAdapter = myAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.patientRecycleView.apply {
            setLayoutManager(layoutManager)


                recycleViewer()
            }

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
            val intent = Intent(requireContext(),PatientRegistrationFormActivity::class.java)
            primaryKey += 1
            intent.putExtra(PRIMARY_KEY,primaryKey)
            startActivity(intent)
            //clears the fragment from stack
            Toast.makeText(context,"Click", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        recycleViewer()
        super.onResume()
    }

    private fun recycleViewer() {
        viewModel.fetchAllRecords().observe(viewLifecycleOwner) { bioData ->
            lifecycleScope.launch {
                Dispatchers.Default
                bioData.forEach {
                    tree[it.patientId] = it


                }
                primaryKey = bioData.size
            }

            myAdapter.submitList(bioData)
            binding.patientRecycleView.adapter = myAdapter
        }
    }


    fun searching(search:String){

      viewModel.searchForPatient(search).observe(viewLifecycleOwner){ bioData ->
            bioData.let {
                myAdapter.submitList(it)

          }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("Bio", "onPause: is called")
    }
    }
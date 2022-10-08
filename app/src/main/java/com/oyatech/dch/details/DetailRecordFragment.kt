package com.oyatech.dch.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.Vitals
import com.oyatech.dch.databinding.FragmentDetailRecordBinding
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [DetailRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailRecordFragment : Fragment() {

     var _binding : FragmentDetailRecordBinding? = null
    val binding get() = _binding!!

    val viewModel  : MedicalHistoryViewModel by activityViewModels()
    var diagnoseId = 0
    private var _listOfVitals: TreeMap<Int, Vitals> = TreeMap()
    val listOfVitals: TreeMap<Int, Vitals> get() = _listOfVitals

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDetailRecordBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuProvider: MenuHost = requireActivity()


        val bundle = this.arguments
        if (bundle != null){
            diagnoseId = bundle.getInt("diagnoseId",0)
        }

        viewModel.setPosition(viewModel.position)

        viewModel.apply {
            //fetching all the vitals and the diagnosis of a patient

            setDiagnosis()
            val di = diagnoses[diagnoseId]!!

          setPosition(di.patientId)
            bindToDiagnose(di)
            fetchAllVitals(position).value?.forEach { vitals ->
                listOfVitals[vitals.vitalsID] = vitals
            }
            listOfVitals[di.vitalsID]?.let { bindDataToViews(it) }

        }

        binding.patientDiagnosisLayout.setOnClickListener{
       Toast.makeText(context, "Click",Toast.LENGTH_SHORT).show()
        }


        menuProvider.addMenuProvider(object :MenuProvider{
            /**
             * Called by the [MenuHost] to allow the [MenuProvider]
             * to inflate [MenuItem]s into the menu.
             *
             * @param menu         the menu to inflate the new menu items into
             * @param menuInflater the inflater to be used to inflate the updated menu
             */
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.visits,menu)
            }

            /**
             * Called by the [MenuHost] when a [MenuItem] is selected from the menu.
             *
             * @param menuItem the menu item that was selected
             * @return `true` if the given menu item is handled by this menu provider,
             * `false` otherwise
             */
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.vital -> {
                        val bundle = Bundle()
                        bundle.putInt("editDiagnose",diagnoseId)
                        findNavController().navigate(R.id.dignosesFragment,bundle)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
//a state that determines when menu should be inflated
        },viewLifecycleOwner,Lifecycle.State.RESUMED)
    }


    private fun lessMoreDetailsLayout(view:View,
                                      textView: TextView,
                                      isMore: ArrayList<Boolean>,
                                      ps:Int){

        if (!isMore.get(ps)){
            view.isVisible = true
            textView.text = "Less Details..."
            isMore[ps] = true

        }else{
            view.isVisible = false
            textView.text = "More Details..."
            isMore[ps] = false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.visits, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.vital -> findNavController().navigate(R.id.dignosesFragment)
        }
        return true
    }
 private  fun bindDataToViews(vitals:Vitals){
        binding.apply {
            with(vitals){
                patientTemperature.text =bodyTemperature
                patientWeight.text = weight
                patientBloodPressure.text = bloodPressure
                patientSugarLevel.text = sugarLevel
                recordedBy.text = recordBy
            }

        }
    }

    private fun bindToDiagnose(diagnose:Diagnose){
        binding.apply {
            with(diagnose){
                provisionalDiagnosis.text = provisional
                principalDiagnose.text = principal
                additionalDiagnosis.text = additional
                dateDiagnose.text = date
                patientPrescription.text = prescription

            }
        }
    }
}
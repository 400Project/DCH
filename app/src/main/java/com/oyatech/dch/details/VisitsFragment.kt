package com.oyatech.dch.details

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentDetailRecordBinding
import com.oyatech.dch.databinding.FragmentVisitsBinding
import com.oyatech.dch.databinding.VisitsCardBinding

/**
 * A simple [Fragment] subclass.
 * Use the [VisitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VisitsFragment : Fragment() {

    private var _binding :FragmentVisitsBinding? =null

    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVisitsBinding.inflate(inflater,container,false)


        return binding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /*binding.addPatientVitals.setOnClickListener {
           Toast.makeText(context,"Vitals Added",Toast.LENGTH_SHORT).show()
        }*/

    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after [.onStop] and before [.onDetach].
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.visits,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.vital ->  findNavController().navigate(R.id.detailRecordFragment)
        }
        return true
    }
}
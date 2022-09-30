package com.oyatech.dch.admin

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.dch.R
import com.oyatech.dch.databinding.FragmentDchStaffBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity

class DchStaffFragment : Fragment() {

    private var _binding: FragmentDchStaffBinding? = null
    private val binding get() = _binding!!
    val viewModel: StaffViewModel by activityViewModels()
    private val DEPARTMENT = "department"
    lateinit var layout: LinearLayoutManager

    val mAdapter: StaffAdapter by lazy {
        StaffAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDchStaffBinding.inflate(
            inflater, container, false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuProvider: MenuHost = requireActivity()
        layout = LinearLayoutManager(requireContext())

        val myAdapter = mAdapter


        recycleViewer()
        binding.addPatient.setOnClickListener {
            findNavController().navigate(R.id.recruit_staff)
        }

        menuProvider.addMenuProvider(object : MenuProvider {
            /**
             * Called by the [MenuHost] to allow the [MenuProvider]
             * to inflate [MenuItem]s into the menu.
             *
             * @param menu         the menu to inflate the new menu items into
             * @param menuInflater the inflater to be used to inflate the updated menu
             */
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.admin, menu)
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
                    R.id.new_account -> {
                        findNavController().navigate(R.id.createAccountFragment)
                        true
                    }
                    R.id.dch_data -> {
                        val intent = Intent(requireContext(), PatientsDataPageActivity::class.java)
                        intent.putExtra(DEPARTMENT,"Admin")
                        requireContext().startActivity(intent
                        )
                        true
                    }
                    R.id.settings -> {
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
//a state that determines when menu should be inflated
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun recycleViewer() {

        binding.staffRecycle.apply {
            layoutManager = layout
            viewModel.fetchStaff().observe(viewLifecycleOwner) { staff ->
                if (staff.isNotEmpty()) {

                    binding.progressBar.visibility = View.INVISIBLE
                }
                adapter = mAdapter

                mAdapter.submitList(staff)


            }
        }
    }

    override fun onPause() {
        super.onPause()
        recycleViewer()
    }

}
package com.oyatech.dch.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.oyatech.dch.R
import com.oyatech.dch.Tests
import com.oyatech.dch.Tests.*
import com.oyatech.dch.databinding.FragmentDignosesBinding


class DiagnosesFragment : Fragment() {

    private var _binding: FragmentDignosesBinding? = null
    private var isTestLayoutVisible = true
    var numberOfTest = SECOND

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDignosesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            showHideIcon.setOnClickListener {
                with(testLayout) {
                    when (isTestLayoutVisible) {
                        false -> {
                            visibility = GONE
                            showHideTest.text = "Show Tests"
                            showHideIcon.setImageResource(R.drawable.ic_arrow_down_24)
                            isTestLayoutVisible = true
                        }
                        else -> {
                            visibility = VISIBLE
                            showHideTest.text = "Hide Tests"
                            isTestLayoutVisible = false
                            showHideIcon.setImageResource(R.drawable.ic_arrow_up_24)
                            add.setOnClickListener {
                                createNumberOfTest(numberOfTest)

                            }
                        }
                    }
                }

            }
        }

    }

    //Creating the number of test layout for test to be conducted
    private fun createNumberOfTest(testNumber: Tests) {
        with(binding) {
            when (testNumber) {
                SECOND -> {
                    test2.visibility = VISIBLE
                    numberOfTest = THIRD

                }
                THIRD -> {
                    test3.visibility = VISIBLE
                    numberOfTest = FOURTH
                }
                FOURTH -> {
                    test4.visibility = VISIBLE
                    numberOfTest = FIFTH

                }
                FIFTH -> {
                    test5.visibility = VISIBLE

                }
                else -> Toast.makeText(requireContext(), "Test Limit reach", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
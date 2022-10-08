package com.oyatech.dch.datacenter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oyatech.dch.Department
import com.oyatech.dch.Department.*
import com.oyatech.dch.consultations.ConsultationsFragment
import com.oyatech.dch.dispensary.DispensaryFragment
import com.oyatech.dch.patient.PatientBioFragment
import com.oyatech.dch.pharmacy.PharmacyFragment
import com.oyatech.dch.vitals.PatientVitalsFragment
import com.oyatech.dch.ward.WardsFragment

class TabAdapter(fragmentActivity: FragmentActivity, department: Department) :
    FragmentStateAdapter(fragmentActivity) {
    var department = department

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter base on the department.
     */

    override fun getItemCount(): Int {
        return when (department) {

            RECORDS -> 1
            OPD -> 1
            IPD -> 3

            else -> Department.values().size
        }

    }

    /**
     * Provide a new Fragment associated with the specified position.
     *
     *
     * The adapter will be responsible for the Fragment lifecycle:
     *
     *  * The Fragment will be used to display an item.
     *  * The Fragment will be destroyed when it gets too far from the viewport, and its state
     * will be saved. When the item is close to the viewport again, a new Fragment will be
     * requested, and a previously saved state will be used to initialize it.
     *
     * @see ViewPager2.setOffscreenPageLimit
     */
    override fun createFragment(position: Int): Fragment {

        return fragmentFactory(position, department)
    }


    @Suppress("UNREACHABLE_CODE")
    private fun fragmentFactory(position: Int, privilege: Department): Fragment {

        if (privilege == RECORDS) {
            return when (position) {
                0 -> PatientBioFragment()
                else -> {
                    throw Resources.NotFoundException("Position not found")
                }
            }
        } else if (privilege == OPD) {
            return when (position) {
                0 -> PatientVitalsFragment()
                else -> {
                    throw Resources.NotFoundException("Position not found")
                }
            }
        } else if (privilege == IPD) {
            return when (position) {
                0 -> PatientVitalsFragment()
                1 -> ConsultationsFragment()
                2 -> WardsFragment()
                else -> {
                    throw Resources.NotFoundException("Position not found")
                }
            }
        } else if(privilege == ADMINISTRATION) {
            return when (position) {
                0 -> PatientBioFragment()
                1 -> PatientVitalsFragment()
                2 -> ConsultationsFragment()
                3 -> WardsFragment()
                4 -> DispensaryFragment()
                5 -> PharmacyFragment()
                else -> {
                    throw Resources.NotFoundException("Position not found")
                }
            }
        }else {
            throw Resources.NotFoundException()
        }
    }
}
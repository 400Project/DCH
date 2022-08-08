package com.oyatech.dch.datacenter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oyatech.dch.patient.PatientRecords
import com.oyatech.dch.consultations.Consultations
import com.oyatech.dch.vitals.PatientVitalsFragment
import com.oyatech.dch.pharmacy.Product



class TabAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
       return 4
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

        return when(position){
            0 -> {
                PatientRecords()
            }
            1 -> {
                PatientVitalsFragment()
            }
            2 -> {
                Consultations()
            }
            3 -> Product()
            else -> {throw Resources.NotFoundException("Position not found")}
        }

    }

}
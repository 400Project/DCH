package com.oyatech.dch.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.databinding.PatientParticularsCardBinding
import com.oyatech.dch.model.Staff


class StaffAdapter() :
    ListAdapter<Staff, StaffAdapter.StaffViewHolder>(DiffUtilCall) {
    lateinit var context:Context
constructor(context: Context) : this() {
    this.context = context
}

    inner class StaffViewHolder
        ( val staffView:PatientParticularsCardBinding)
        :ViewHolder(staffView.root){
        fun bindData(staff: Staff){

            staffView.apply {

                    firstName.text = staff.firstName
                    otherName.text = staff.otherName
                    recordedBy.text = staff.department
                    timeRecorded.text = staff.dateEmployed
                    hospitalNumberTextView.text = staff.role

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
    //Inflating the layout for the onCreate to
        // create views for onBind to bind data to the views
     val    binding = PatientParticularsCardBinding
         .inflate(LayoutInflater.from(context)
             ,parent,false)

        return  StaffViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

}

object DiffUtilCall: DiffUtil.ItemCallback<Staff>() {

    override fun areItemsTheSame(oldItem: Staff, newItem: Staff): Boolean {
        //Using the id of the patient to check
        return newItem.hashCode() ==oldItem.hashCode()
    }


    override fun areContentsTheSame(oldItem: Staff, newItem: Staff): Boolean {
        return newItem.equals(oldItem)
    }

}

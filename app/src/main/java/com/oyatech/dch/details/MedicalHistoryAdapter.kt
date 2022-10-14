package com.oyatech.dch.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oyatech.dch.R
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.databinding.HistoryCardBinding
import com.oyatech.dch.details.MedicalHistoryAdapter.MedicalHistoryViewHolder

class MedicalHistoryAdapter(context: Context,fragment:Fragment) :
    ListAdapter<Diagnose, MedicalHistoryViewHolder>(DiffCall) {
val context = context
    val fragment = fragment

    inner class MedicalHistoryViewHolder(val binding: HistoryCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindToHistory(diagnose: Diagnose) {
            diagnose.apply {
                with(binding) {
                    principalDiagnosisHistory.text = principal
                    visitDate.text = date
                    doctorName.text = staffName
                    patientStatus.text = treatmentStatus
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalHistoryViewHolder {
        val binding = HistoryCardBinding
            .inflate(
                LayoutInflater.from(parent.context), parent, false
            )

        return MedicalHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicalHistoryViewHolder, position: Int) {
//Setting the data from the diagnoses to the appropriate views
        val diagnose: Diagnose = getItem(position)
        diagnose.let {
        holder.apply {
            //call the helper method from the onCreateView by passing in the diagnoses object
            bindToHistory(diagnose)
        } }

        holder.apply {
            itemView.setOnClickListener {
                val diagnoseId = getItem(adapterPosition).diagnoseID
                val vitalId = adapterPosition

                val bundle = Bundle()
                bundle.putInt("diagnoseId",diagnoseId)
                bundle.putInt("vitalsId",vitalId)
fragment.findNavController().navigate(R.id.detailRecordFragment,bundle)
                Log.i("MediHistory", "onBindViewHolder: $bundle")

            }
        }
    }


    override fun getItemCount() = currentList.size


    companion object DiffCall : DiffUtil.ItemCallback<Diagnose>() {
        /**
         * Called to check whether two objects represent the same item.
         *
         *
         * For example, if your items have unique ids, this method should check their id equality.
         *
         *
         * Note: `null` items in the list are assumed to be the same as another `null`
         * item and are assumed to not be the same as a non-`null` item. This callback will
         * not be invoked for either of those cases.
         *
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return True if the two items represent the same object or false if they are different.
         *
         * @see Callback.areItemsTheSame
         */
        override fun areItemsTheSame(oldItem: Diagnose, newItem: Diagnose): Boolean {
            return oldItem.diagnoseID == newItem.diagnoseID
        }

        /**
         * Called to check whether two items have the same data.
         *
         *
         * This information is used to detect if the contents of an item have changed.
         *
         *
         * This method to check equality instead of [Object.equals] so that you can
         * change its behavior depending on your UI.
         *
         *
         * For example, if you are using DiffUtil with a
         * [RecyclerView.Adapter], you should
         * return whether the items' visual representations are the same.
         *
         *
         * This method is called only if [.areItemsTheSame] returns `true` for
         * these items.
         *
         *
         * Note: Two `null` items are assumed to represent the same contents. This callback
         * will not be invoked for this case.
         *
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return True if the contents of the items are the same or false if they are different.
         *
         * @see Callback.areContentsTheSame
         */
        override fun areContentsTheSame(oldItem: Diagnose, newItem: Diagnose): Boolean {
           return oldItem == newItem
        }

    }

    fun intentToDiagnoseDetails(holder:MedicalHistoryViewHolder){


    }
}
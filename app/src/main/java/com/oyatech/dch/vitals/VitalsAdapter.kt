package com.oyatech.dch.vitals

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oyatech.dch.database.entities.PatientBioData

import com.oyatech.dch.databinding.PatientParticularsCardBinding



class VitalsAdapter():ListAdapter<PatientBioData,
        VitalsAdapter.VitalsViewHolder>(DiffUtilCall){
  private val DUE_FOR_VITALS = "com.oyatech.dch.vitals"
    lateinit var context: Context

    constructor(context: Context):this(){
        this.context = context
    }

    inner class VitalsViewHolder(var vitalLayout:PatientParticularsCardBinding)
        :RecyclerView.ViewHolder(vitalLayout.root){

        fun binder(patientBioData: PatientBioData){
            vitalLayout.firstName.text = patientBioData.first_Name
            vitalLayout.otherName.text = patientBioData.otherNames
        }


    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VitalsViewHolder {
       val vitalLayout = PatientParticularsCardBinding.inflate(LayoutInflater.from(context),parent,false)
        return VitalsViewHolder(vitalLayout)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: VitalsViewHolder, position: Int) {
        with(holder){
                binder(getItem(position))
        }


        holder.itemView.setOnClickListener{
            val currentPatientPos = getItem(holder.adapterPosition).patientId
            val vitalsIntent = Intent(context.applicationContext,VitalsActivity::class.java)

            vitalsIntent.putExtra(DUE_FOR_VITALS,currentPatientPos)
            context.startActivity(vitalsIntent)
        }

    }

    override fun getItemCount():Int{
        return currentList.size
    }


}

object DiffUtilCall: DiffUtil.ItemCallback<PatientBioData>() {
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
    override fun areItemsTheSame(oldItem: PatientBioData, newItem: PatientBioData): Boolean {
        //Using the id of the patient to check
        return newItem.hashCode() ==oldItem.hashCode()
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
    override fun areContentsTheSame(oldItem: PatientBioData, newItem: PatientBioData): Boolean {
       return newItem == oldItem
    }

}

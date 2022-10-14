package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.PatientBioData

interface IWard {
    //inserting admitted or detain patient into the word db
    fun insertWard(patient: PatientBioData)
    //removing a discharged patient from the ward
    fun removeFromWard(position: Int)
    //loading all the patient int the ward
    fun fetchWard(): LiveData<MutableList<PatientBioData>>
}
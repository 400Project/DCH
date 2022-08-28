package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.PatientBioData

import com.oyatech.dch.database.entities.Vitals

interface IRepository {
    //insert patient bio data into the database
    fun insertPatientBio(patientBioData: PatientBioData)
    fun getAllBioData():LiveData<MutableList<PatientBioData>>
    fun currentBio(int:Int):PatientBioData

    fun queueForVitals(dailyVitals: DailyVitals)
    fun getQueueForVitals(): LiveData<MutableList<DailyVitals>>



    fun insertVitals(vitals: Vitals)
}
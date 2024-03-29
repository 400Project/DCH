package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.NextOfKin
import com.oyatech.dch.database.entities.PatientBioData

interface IPatient {
    //insert patient bio data into the database

    fun queueForVitals(dailyVitals: DailyVitals)
  //  fun getQueueForVitals(): LiveData<MutableList<DailyVitals>>

    //Firebase calls
    fun insertPatientFirestore(patientBioData: PatientBioData)
   fun fetchAllRecords(): LiveData<MutableList<PatientBioData>>
   fun searchPatient(query:String): LiveData<MutableList<PatientBioData>>

   fun insertNextOfKin(nextOfKin: NextOfKin)

 ///  fun insertDailyVitals(patientBioData: PatientBioData)


}
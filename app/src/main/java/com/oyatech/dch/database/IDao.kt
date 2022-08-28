package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.PatientVitals
import com.oyatech.dch.database.entities.Vitals

@Dao
interface IDao {
    @Insert
    fun insertBioData(bioData: PatientBioData)

    @Query ("SELECT * from PatientBioData")
    fun getAllBioData():LiveData<MutableList<PatientBioData>>

    @Query ("SELECT * from PatientBioData WHERE patientId = :id")
    fun allPatients(id:Int):PatientBioData

    @Insert
    fun queueForVitals(bioData: DailyVitals )

    @Transaction
        @Query("SELECT * FROM DailyVitals")
    fun getPatientBioAndId():LiveData<MutableList<DailyVitals>>

    @Query ("SELECT * from  DailyVitals WHERE id = :id")
    fun getCurrentPatientForVitals(id:Int):PatientBioData
    @Insert
    fun insertVitals(vitals: Vitals)

    //Selecting all the vitals of each user in the db
   /* @Transaction
    @Query("SELECT * FROM PatientBioData")
    fun patientAndVitals():MutableList<PatientBioData>*/
}
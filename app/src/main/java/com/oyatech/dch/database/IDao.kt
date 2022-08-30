package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.oyatech.dch.database.entities.*

@Dao
interface IDao {
    @Insert
    fun insertBioData(bioData: PatientBioData)

    @Query ("SELECT * from PatientBioData ORDER BY patientId DESC")
    fun getAllBioData():LiveData<MutableList<PatientBioData>>

    @Query ("SELECT * from PatientBioData WHERE patientId = :id")
    fun allPatients(id:Int):PatientBioData

    @Query("SELECT * FROM PatientBioData WHERE first_name LIKE :search||'%'")
fun searchForPatient(search:String):LiveData<MutableList<PatientBioData>>

    @Insert
    fun queueForVitals(bioData: DailyVitals )

        @Query("SELECT * FROM DailyVitals")
    fun getPatientBioAndId():LiveData<MutableList<DailyVitals>>

    @Query ("SELECT * from  DailyVitals WHERE id = :id")
    fun getCurrentPatientForVitals(id:Int):PatientBioData
    @Insert
    fun insertVitals(vitals: Vitals)

    @Insert
    fun bookForConsultation(bioData: DailyConsultation )

    @Query("SELECT * FROM DailyConsultation")
    fun getAllBookedForConsultation():LiveData<MutableList<DailyConsultation>>

    @Query ("SELECT * from  DailyConsultation WHERE consultID = :id")
    fun getCurrentPatientForConsult(id:Int):PatientBioData


}
package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import androidx.room.*
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
    fun getQueueForVitals():LiveData<MutableList<DailyVitals>>

    @Delete
    fun removePatientFromVitalsQue(dialVitals: DailyVitals)
    @Query ("SELECT * from  DailyVitals WHERE patientId = :id")
    fun getCurrentPatientForVitals(id:Int):DailyVitals
    @Insert
    fun insertVitals(vitals: Vitals)

    @Query("SELECT * FROM Vitals WHERE foreignKyePatient = :id")
    fun getCurrentVitals(id: Int):MutableList<Vitals>

    //Diagnosis
    @Insert
    fun insertDiagnoses(diagnose: Diagnose)
    @Query("SELECT * from Diagnose WHERE parentID == :foreignKey  ORDER BY diagnoseID DESC ")
    fun getAllPatientDiagnoses(foreignKey: Int):LiveData<MutableList<Diagnose>>

    //A table that contains a temporal daily consultation list
    @Insert
    fun bookForConsultation(bioData: DailyConsultation )

    @Query("SELECT * FROM DailyConsultation")
    fun getAllBookedForConsultation():LiveData<MutableList<DailyConsultation>>

    @Query ("SELECT * from  DailyConsultation WHERE patientId = :id")
    fun getDailConsultationByID(id:Int):DailyConsultation


}
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

    @Query("SELECT * FROM Vitals WHERE patientId = :id")
    fun getCurrentVitals(id: Int):MutableList<Vitals>

    //Diagnosis
    @Insert
    fun insertDiagnoses(diagnose: Diagnose)

    @Query("SELECT * from Diagnose WHERE parentID == :foreignKey  ORDER BY diagnoseID DESC ")
    fun getAllPatientDiagnoses(foreignKey: Int):LiveData<MutableList<Diagnose>>
  /* @Query("SELECT * FROM Diagnose ,Vitals on PatientBioData.patientId ==Diagnose.parentID " +
            "and PatientBioData.patientId == Vitals.foreignKyePatient" +
            " WHERE parentID == :id group by Vitals.vID" )
    fun getDiagnose(id:Int, date:String):MutableList<MedicalHistory>*/



    //A table that contains a temporal daily consultation list
    @Insert
    fun bookForConsultation(bioData: DailyConsultation )

    @Query("SELECT * FROM DailyConsultation")
    fun getAllBookedForConsultation():LiveData<MutableList<DailyConsultation>>

    @Query ("SELECT * from  DailyConsultation WHERE patientId = :id")
    fun getDailConsultationByID(id:Int):DailyConsultation
    @Delete
    fun removeFromDailyConsultation(dailyConsultation: DailyConsultation)


    //Produce auto increment for Vitals table
    @Insert
    fun insertVitalsIDs( vitals: ViDs)

    @Query ("UPDATE ViDs set vitalsID =:current where vitalsID =:prev")
    fun updateVitalsIDs(prev: Int, current:Int)
    @Query ("SELECT vitalsID FROM ViDs")
    fun getVitalsIDs():Int

//Produce auto increment for Diagnosis table
    @Insert
    fun insertDiagnoseIDs( diagID: DiagID)

    @Query ("UPDATE DiagID set diagnoseID =:current where diagnoseID =:previous")
    fun updateDiagnoseIDs(previous: Int, current:Int)
    @Query ("SELECT diagnoseID FROM DiagID")
    fun getDiagnoseIDs():Int
}
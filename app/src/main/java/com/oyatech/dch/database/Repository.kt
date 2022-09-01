package com.oyatech.dch.database


import android.app.Application
import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.*

class Repository(application: Application):IRepository,IConsult {
    private var _mao : IDao? = null
    private val mDao get() =_mao!!

    init {
        val mIDao = DCHDatabase.getDatabaseInstance(application)
        _mao = mIDao.mDao()

    }

    /**
     * The Patient Bio data table for all records
     */
    override fun insertPatientBio(patientBioData: PatientBioData) {
        mDao.insertBioData(patientBioData)
    }

    override fun getAllBioData(): LiveData<MutableList<PatientBioData>> {

       return mDao.getAllBioData()
    }

    override fun currentBio(int:Int):PatientBioData{
        return mDao.allPatients(int)
    }

    override fun searchForPatient(search: String): LiveData<MutableList<PatientBioData>> {
        return mDao.searchForPatient(search)
    }

    /**
     * The Whole Vitals table that contains all vitals of the patient records
     */
    fun insertVitals(vitals: Vitals){
        mDao.insertVitals(vitals)
    }

    override fun getCurrentVitals(id: Int): Vitals {

        return mDao.getCurrentVitals(id).last()  }

    fun getCurrentPatientForVitals(id: Int): PatientBioData {
     return mDao.getCurrentPatientForVitals(id).patientBioData
    }




    /**
     * Daily Vitals list.
     */
    override fun queueForVitals(dailyVitals: DailyVitals){
        mDao.queueForVitals(dailyVitals)
    }
    override  fun getQueueForVitals(): LiveData<MutableList<DailyVitals>>{

        return mDao.getQueueForVitals()
    }
    fun getCurrentQueVitals(id: Int): DailyVitals {
        return mDao.getCurrentPatientForVitals(id)
    }
    fun removePatientFromVitalsQue(dialVitals: DailyVitals){
        mDao.removePatientFromVitalsQue(dialVitals)
    }


    /**
     * Daily Consultation table
     */
  override  fun bookForConsultation(bioData: DailyConsultation){
        mDao.bookForConsultation(bioData)
    }

    override  fun getAllBookedForConsultation():LiveData<MutableList<DailyConsultation>>{

        return mDao.getAllBookedForConsultation()
    }

    override   fun getDailConsultationByID(id:Int):DailyConsultation{
        return mDao.getDailConsultationByID(id)
    }
    override fun getCurrentPatientAtConsultation(id: Int): PatientBioData {
        return mDao.getDailConsultationByID(id).bios
    }


    /**
     * The Diagnoses table for all diagnoses of all records
     */
    override fun getAllPatientDiagnoses(foreignKey: Int): LiveData<MutableList<Diagnose>> {
        return mDao.getAllPatientDiagnoses(foreignKey)
    }

    override fun insertDiagnosis(diagnose: Diagnose) {
        mDao.insertDiagnoses(diagnose)
    }


}
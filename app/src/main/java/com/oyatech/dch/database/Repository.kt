package com.oyatech.dch.database


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.Vitals

class Repository(application: Application):IRepository,IConsult {
    private var _mao : IDao? = null
    private val mDao get() =_mao!!

    init {
        val mIDao = DCHDatabase.getDatabaseInstance(application)
        _mao = mIDao.mDao()

    }


    override fun insertPatientBio(patientBioData: PatientBioData) {
        mDao.insertBioData(patientBioData)
    }

    override fun getAllBioData(): LiveData<MutableList<PatientBioData>> {

       return mDao.getAllBioData()
    }

    fun insertVitals(vitals: Vitals){
        mDao.insertVitals(vitals)
    }

 override  fun getQueueForVitals(): LiveData<MutableList<DailyVitals>>{

       return mDao.getPatientBioAndId()
    }

   fun getCurrentPatientForVitals(id: Int): PatientBioData {
     return mDao.getCurrentPatientForVitals(id)
    }

    override fun currentBio(int:Int):PatientBioData{
        return mDao.allPatients(int)
    }

    override fun searchForPatient(search: String): LiveData<MutableList<PatientBioData>> {
        return mDao.searchForPatient(search)
    }

    override fun queueForVitals(dailyVitals: DailyVitals){
        mDao.queueForVitals(dailyVitals)
    }

  override  fun bookForConsultation(bioData: DailyConsultation){
        mDao.bookForConsultation(bioData)
    }

    override  fun getAllBookedForConsultation():LiveData<MutableList<DailyConsultation>>{

        return mDao.getAllBookedForConsultation()
    }

    override   fun getCurrentPatientForConsult(id:Int):PatientBioData{
        return mDao.getCurrentPatientForConsult(id)
    }
}
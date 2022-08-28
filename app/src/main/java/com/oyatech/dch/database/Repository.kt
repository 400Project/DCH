package com.oyatech.dch.database


import android.app.Application
import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.Vitals

class Repository(application: Application):IRepository {
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

    override fun insertVitals(vitals: Vitals){
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
    override fun queueForVitals(dailyVitals: DailyVitals){
        mDao.queueForVitals(dailyVitals)
    }
}
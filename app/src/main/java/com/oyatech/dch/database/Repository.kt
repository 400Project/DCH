package com.oyatech.dch.database


import android.app.Application
import androidx.room.RoomDatabase
import com.oyatech.dch.database.entities.PatientBioData

class Repository(application: Application):IRepository {
    private var mao : IDao? = null
    init {
        val mIDao = DCHDatabase.getDatabaseInstance(application)
        mao = mIDao.mDao()
    }




    override fun insertPatientBio(patientBioData: PatientBioData) {
        mao?.insertBioData(patientBioData)
    }
}
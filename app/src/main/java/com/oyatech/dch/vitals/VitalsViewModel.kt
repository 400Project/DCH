package com.oyatech.dch.vitals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.Vitals
import com.oyatech.dch.model.DataSource



class VitalsViewModel(application: Application) :  AndroidViewModel(application){
    // TODO: Implement the ViewModel
    private var _repository : Repository? = null
     private val repository get()=_repository!!

    /*private var _queueForVitals :LiveData<MutableList<PatientBioData>> = LiveData<MutableList<PatientBioData>>()
    val queuedForVitals :LiveData<MutableList<PatientBioData>> = _queueForVitals*/
    init {
        _repository = Repository(application)

        }



     fun getCurrentPatientForVitals(id: Int): PatientBioData {
        return repository.getCurrentPatientForVitals(id)

    }

    fun removeFromVitalsQue(particular: PatientBioData){
       // _queueForVitals.remove(particular)
    }
    fun clearVitalsList(){
       DataSource.allPatient().clear()
    }

    fun insertVitals( vitals: Vitals){
        repository.insertVitals(vitals)
    }
     fun patientAndVitals(): LiveData<MutableList<DailyVitals>>{
        return repository.getQueueForVitals()
    }
    fun bookForConsultation(bioData: DailyConsultation){
        return repository.bookForConsultation(bioData)
    }
}
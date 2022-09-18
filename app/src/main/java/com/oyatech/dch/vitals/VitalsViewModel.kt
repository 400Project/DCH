package com.oyatech.dch.vitals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.*
import com.oyatech.dch.model.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VitalsViewModel(application: Application)
    :  AndroidViewModel(application){
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
    fun getCurrentQueVitals(id: Int): DailyVitals {
        return repository.getCurrentQueVitals(id)
    }
    fun removePatientFromVitalsQue(int: Int){
        viewModelScope.launch {
            repository.removeVitalsQue(int)
        }
    }
    fun clearVitalsList(){
       DataSource.allPatient().clear()
    }

    fun insertVitalsIDs( vitals: ViDs){
        repository.insertVitalsIDs(vitals)
    }
    fun updateVitalsIDs(prev: Int, current:Int){
        viewModelScope.launch {
            Dispatchers.IO
            repository.updateVitalsIDs(prev, current)
        }

    }

    fun getVitalsIDs():Int{
        return repository.getVitalsIDs()
    }

    /* fun patientAndVitals(): LiveData<MutableList<DailyVitals>>{
        return repository.getQueueForVitals()
    }*/
    fun bookForConsultation(bioData: DailyConsultation){
        return repository.bookForConsultation(bioData)
    }

    /**
     * Firebase activities
     */


    fun fetchDailyVitals(): LiveData<MutableList<DailyVitals>>{
        var allRecords:LiveData<MutableList<DailyVitals>> = MutableLiveData()
        viewModelScope.launch { Dispatchers.IO
          allRecords =  repository.fetchDailyVitals()
        }
        return allRecords
    }

    fun  insertVitalsOnline( patientID: Int, vitals: Vitals){
        repository.insertVitalsRemote(patientID,vitals)
    }

    fun insertDailyConsultation(consult: DailyConsultation){
        viewModelScope.launch {
            Dispatchers.Default
            repository.insertDailyConsultation(consult)
        }
    }
  fun getCurrentQueVitalsOnline(id: DailyVitals){

  }
}
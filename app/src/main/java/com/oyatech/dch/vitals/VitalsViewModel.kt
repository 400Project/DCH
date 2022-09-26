package com.oyatech.dch.vitals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.IVitalsId
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.*
import com.oyatech.dch.model.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VitalsViewModel(application: Application)
    :  AndroidViewModel(application),IVitalsId{
    // TODO: Implement the ViewModel
    private var _repository : Repository? = null
     private val repository get()=_repository!!

    /*private var _queueForVitals :LiveData<MutableList<PatientBioData>> = LiveData<MutableList<PatientBioData>>()
    val queuedForVitals :LiveData<MutableList<PatientBioData>> = _queueForVitals*/
    init {
        _repository = Repository(application)

        }


    fun removePatientFromVitalsQue(int: Int){
        viewModelScope.launch {

            repository.removeVitalsQue(int)
        }
    }

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

    fun  insertVitalsOnline(vitals: Vitals){
        repository.insertVitalsRemote(vitals)
    }

    fun insertDailyConsultation(consult: DailyConsultation){
        viewModelScope.launch {

            repository.insertDailyConsultation(consult)
        }
    }
  fun getCurrentQueVitalsOnline(id: DailyVitals){

  }

    override fun insertVitalId(id: ViDs) {
        viewModelScope.launch {
            repository.insertVitalId(id)
        }
    }

    override fun getVitalsId(): LiveData<Int>{
    return    repository.getVitalsId()
    }
}
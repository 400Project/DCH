package com.oyatech.dch.patient

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.IRepository
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.PatientBioData


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PatientBioViewModel(application: Application) :
    AndroidViewModel(application),IRepository {

    private var _repository :Repository? = null
    private val repository get()=_repository!!

var number = 0

    init {
        _repository = Repository(application)
    }

  override  fun insertPatientBio(patientBioData: PatientBioData){
        viewModelScope.launch { Dispatchers.IO
            repository.insertPatientBio(patientBioData)
        }
    }

   /* override fun getQueueForVitals(): LiveData<MutableList<DailyVitals>> {
        return repository.getQueueForVitals()
    }*/


    override fun getAllBioData():LiveData<MutableList<PatientBioData>>{
       return repository.getAllBioData()
   }

    //val allPatient = listOfPatientPaticulars

    override    fun currentBio(int:Int): PatientBioData {
      return  repository.currentBio(int)
    }

    override fun searchForPatient(search: String): LiveData<MutableList<PatientBioData>> {
        return repository.searchForPatient(search)
    }

    override fun queueForVitals(dailyVitals: DailyVitals) {
        return repository.queueForVitals(dailyVitals)
    }


    /*
* The following methods communicates with the firebase cloud firestore
* */


    override fun insertPatientFirestore(patientBioData: PatientBioData) {
     viewModelScope.launch {
         Dispatchers.IO
         repository.insertPatientFirestore(patientBioData)
     }
    }

    fun insertDailyVitals(dailyVitals: DailyVitals){
        viewModelScope.launch {
            Dispatchers.IO
            repository.insertDailyVitals(dailyVitals)
        }

    }
 override fun fetchAllRecords(): LiveData<MutableList<PatientBioData>> {
        var allRecords:LiveData<MutableList<PatientBioData>> = MutableLiveData()
        viewModelScope.launch { Dispatchers.IO
            allRecords =  repository.fetchAllRecords()
        }
    return allRecords
    }


}
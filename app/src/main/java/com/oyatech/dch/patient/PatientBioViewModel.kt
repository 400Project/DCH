package com.oyatech.dch.patient

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.IPatient
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.DailyVitals
import com.oyatech.dch.database.entities.NextOfKin
import com.oyatech.dch.database.entities.PatientBioData


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PatientBioViewModel(application: Application) :
    AndroidViewModel(application),IPatient {

    private var _repository :Repository? = null
    private val repository get()=_repository!!

var number = 0

    init {
        _repository = Repository(application)
    }




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

    override fun searchPatient(query: String): LiveData<MutableList<PatientBioData>> {
       var result:LiveData<MutableList<PatientBioData>> = MutableLiveData()
        viewModelScope.launch {
            Dispatchers.Default
         result =   repository.searchPatient(query)
        }
        return result
    }

    override fun insertNextOfKin(nextOfKin: NextOfKin) {
        viewModelScope.launch {
            Dispatchers.IO
            repository.insertNextOfKin(nextOfKin)

        }
    }


}
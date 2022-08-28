package com.oyatech.dch.database.entities

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.IRepository
import com.oyatech.dch.database.Repository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class PatientBioViewModel(application: Application) :
    AndroidViewModel(application),IRepository {

    private var _repository :Repository? = null
    private val repository get()=_repository!!



    init {
        _repository = Repository(application)
    }

  override  fun insertPatientBio(patientBioData: PatientBioData){
        viewModelScope.launch { Dispatchers.IO
            repository.insertPatientBio(patientBioData)
        }
    }

   override fun insertVitals(vitals: Vitals){
        viewModelScope.launch {
            Dispatchers.IO
            repository.insertVitals(vitals)
        }
    }

    override fun getQueueForVitals(): LiveData<MutableList<DailyVitals>> {
        return repository.getQueueForVitals()
    }


    override fun getAllBioData():LiveData<MutableList<PatientBioData>>{
       return repository.getAllBioData()
   }

    //val allPatient = listOfPatientPaticulars
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateAndTime(): String {
        val sdf = SimpleDateFormat("dd/M/y hh:m aa")
        val calender = Calendar.getInstance()
        return sdf.format(calender.time)
    }

    override    fun currentBio(int:Int):PatientBioData{
      return  repository.currentBio(int)
    }

    override fun queueForVitals(dailyVitals: DailyVitals) {
        return repository.queueForVitals(dailyVitals)
    }


}
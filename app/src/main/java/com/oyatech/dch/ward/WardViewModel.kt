package com.oyatech.dch.ward

import android.app.Application
import androidx.lifecycle.*
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.IWard
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.database.entities.PatientBioData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WardViewModel(application: Application) : AndroidViewModel(application), IWard {

    private var _repository : Repository? = null
    private val repository get()=_repository!!


    private var _position = 0
    val position get() = _position

    init {
        _repository = Repository(application)
    }


   override fun insertWard(patient: PatientBioData){
       viewModelScope.launch {
           Dispatchers.Default
           repository.insertWard(patient)
       }
    }

    override fun removeFromWard(position: Int) {
        repository.removeFromWard(position)
    }

  override  fun fetchWard(): LiveData<MutableList<PatientBioData>> {
      var patient:LiveData<MutableList<PatientBioData>> = MutableLiveData()

      viewModelScope.launch {
          Dispatchers.Default
          patient = repository.fetchWard()
      }
     return patient
    }

}
package com.oyatech.dch.ward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oyatech.dch.model.DataSource
import com.oyatech.dch.model.PatientBioData

class WarViewModel : ViewModel(){

    private var _patientAtWard :MutableLiveData<MutableList<PatientBioData>> = MutableLiveData<MutableList<PatientBioData>>()
    val patientsInWard :LiveData<MutableList<PatientBioData>> = _patientAtWard

    init {
        _patientAtWard.value = DataSource.allWardPatient()
    }
    fun getCurrentVitalQueue(position: Int): PatientBioData {
        return _patientAtWard.value!![position]
    }
    fun removeFromVitalsQue(particular: PatientBioData){
        // _queueForVitals.remove(particular)
    }
    fun clearVitalsList(){
        DataSource.allPatient().clear()
    }

}
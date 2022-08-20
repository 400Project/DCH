package com.oyatech.dch.consultations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oyatech.dch.model.DataSource
import com.oyatech.dch.model.DataSource.consultQueue
import com.oyatech.dch.model.PatientBioData

class ConsultationViewModel: ViewModel() {

    private var _queuedForConsultation= MutableLiveData<MutableList<PatientBioData>>()
    val queuedForConsultation:LiveData<MutableList<PatientBioData>> = _queuedForConsultation

    init {
        _queuedForConsultation.value = consultQueue()
    }

    fun getCurrentQueuedForConsultation(position:Int): PatientBioData? {
        return _queuedForConsultation.value?.get(position)
    }
    fun removeFromQue(patientBioData: PatientBioData){
        _queuedForConsultation.removeObserver { it.remove(patientBioData) }
    }
    fun clearConsultationQue(){
        _queuedForConsultation.value = null
    }
}
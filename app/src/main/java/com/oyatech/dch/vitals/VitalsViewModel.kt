package com.oyatech.dch.vitals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oyatech.dch.model.DataSource
import com.oyatech.dch.model.PatientBioData
import com.oyatech.dch.model.listOfPatientPaticulars
import com.oyatech.dch.observer.IOObserver

class VitalsViewModel : ViewModel(),IOObserver{
    // TODO: Implement the ViewModel
    private var _queueForVitals :MutableLiveData<MutableList<PatientBioData>> = MutableLiveData<MutableList<PatientBioData>>()
         val queuedForVitals :LiveData<MutableList<PatientBioData>> = _queueForVitals

init {
    _queueForVitals.value = DataSource.allPatient()
}
    fun setQueuedForVitals(patientBioData: PatientBioData){

    }
   /*fun getQueuedForVitals():MutableList<PatientBioData>{
        return queuedForVitals
    }*/
    fun getCurrentVitalQueue(position: Int): PatientBioData {
        return _queueForVitals.value!![position]
    }
    fun removeFromVitalsQue(particular: PatientBioData){
       // _queueForVitals.remove(particular)
    }
    fun clearVitalsList(){
       DataSource.allPatient().clear()
    }

    override fun opdUpdate() {
        TODO("Not yet implemented")
    }
}
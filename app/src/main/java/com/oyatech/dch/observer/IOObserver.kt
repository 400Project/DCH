package com.oyatech.dch.observer

import com.oyatech.dch.model.PatientBioData
import com.oyatech.dch.vitals.VitalsViewModel

//Vital (OPD) will serve as an observer to new patients been book
//his/her vitals to be taken
interface IOObserver {
    fun opdUpdate()
}

//New patient record at the Record department to be observed by the OPD
interface IOObservable{
    val vitalOpd: MutableList<VitalsViewModel>
    fun addPatientToOPD(ioObserver: VitalsViewModel){

        vitalOpd.add(ioObserver)
    }

    fun sendOPDUpdate(ioObserver: VitalsViewModel){
     //   vitalOpd.forEach { it.getQueuedForVitals() }
    }
}
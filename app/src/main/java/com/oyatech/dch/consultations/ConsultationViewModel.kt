package com.oyatech.dch.consultations

import androidx.lifecycle.ViewModel
import com.oyatech.dch.model.PatientBioData

class ConsultationViewModel: ViewModel() {

  private  var _queForConsultation = ArrayList<PatientBioData> ()
    val queForConsultation: List<PatientBioData> = _queForConsultation



    fun getConsultationQue(patientBioData: PatientBioData){
         _queForConsultation.add(patientBioData)
    }
}
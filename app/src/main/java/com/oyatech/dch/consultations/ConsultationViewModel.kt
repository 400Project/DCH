package com.oyatech.dch.consultations

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.patient.data.Particulars

class ConsultationViewModel: ViewModel() {

  private  var _queForConsultation = ArrayList<Particulars> ()
    val queForConsultation: List<Particulars> = _queForConsultation



    fun getConsultationQue(particulars: Particulars){
         _queForConsultation.add(particulars)
    }
}
package com.oyatech.dch.patient

import android.os.Build
import androidx.annotation.RequiresApi

import androidx.lifecycle.ViewModel
import com.oyatech.dch.patient.data.Particulars
import com.oyatech.dch.patient.data.listOfPatientPaticulars
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegisterNewPatientViewModel : ViewModel() {
  private  val _patientsList : MutableList<Particulars> = listOfPatientPaticulars
    val patientList:MutableList<Particulars> = _patientsList

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateAndTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"))
    }


    fun getPatientParticulars(firsName:String,otherName:String)
    { val newPatient = Particulars(firsName,otherName)
            _patientsList.add(newPatient)

    }

    fun getConsultationQue(particulars: Particulars){
        _patientsList.add(particulars)
    }

}
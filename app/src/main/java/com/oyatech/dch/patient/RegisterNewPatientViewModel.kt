package com.oyatech.dch.patient

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oyatech.dch.patient.data.Particulars
import com.oyatech.dch.patient.data.listOfPatientPaticulars
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegisterNewPatientViewModel : ViewModel() {
  //  private val listOfParticulars = PatientRepository(listOfPatientPaticulars)
  private  val _patientsList : MutableList<Particulars> = listOfPatientPaticulars
    val patientList: LiveData<List<Particulars>> = liveData { _patientsList }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun getDateAndTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"))
    }


    fun addPatients(firsName:String,otherName:String,date:String)
    {
            _patientsList.add(Particulars(firsName,otherName))

    }

    fun removePatients(particulars: Particulars){
        _patientsList.remove(particulars)
    }

    fun getConsultationQue(particulars: Particulars){
       _patientsList.add(particulars)
    }

}
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

  private  val patientsList : MutableLiveData<MutableList<Particulars>>? = MutableLiveData()
  //  val patientList: LiveData<List<Particulars>> = liveData { _patientsList }
companion object{
    var particularsList=Particulars("","")
}


  val allPatient = listOfPatientPaticulars
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateAndTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"))
    }


    fun setPatient(particulars: Particulars){
      particularsList =particulars
    }


    fun setParticulars(){
        patientsList?.value = listOfPatientPaticulars
    }

    fun getConsultationQue(particulars: Particulars){
  //     _patientsList.add(particulars)
    }

    fun getPatient():MutableLiveData<MutableList<Particulars>>{
        return patientsList!!
    }
}
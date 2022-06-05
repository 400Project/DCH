package com.oyatech.dch.patient

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.oyatech.dch.patient.data.Paticulars
import com.oyatech.dch.patient.data.listOfPatientPaticulars
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegisterNewPatientViewModel : ViewModel() {
    private var _patientsList :ArrayList<Paticulars> = listOfPatientPaticulars
   var patientList:List<Paticulars> = _patientsList

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateAndTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"))
    }


    fun getPatientParticulars(firsName:String,otherName:String)
    { val newPatient = Paticulars(firsName,otherName)
        _patientsList.add(newPatient)

    }

}
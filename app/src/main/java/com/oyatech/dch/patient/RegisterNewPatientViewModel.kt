package com.oyatech.dch.patient

import android.os.Build
import androidx.annotation.RequiresApi

import androidx.lifecycle.ViewModel
import com.oyatech.dch.patient.data.Particulars
import java.text.SimpleDateFormat
import java.util.*

class RegisterNewPatientViewModel : ViewModel() {
  //  private val listOfParticulars = PatientRepository(listOfPatientPaticulars)

    companion object{
        val viewModel = RegisterNewPatientViewModel()
        private val patientsList : MutableList<Particulars> = mutableListOf()

    }

private  var _bioList :MutableList<Particulars> = mutableListOf()
private val bioList = _bioList

    private var _queuedForConsultation:MutableList<Particulars> = mutableListOf()
    private  val queuedForConsultation = _queuedForConsultation

    private var _queueForVitals :MutableList<Particulars> = mutableListOf()
    private val queuedForVitals = _queueForVitals

    var registerViewMode :RegisterNewPatientViewModel? =null


  //  val patientList: LiveData<List<Particulars>> = liveData { _patientsList }

    //val allPatient = listOfPatientPaticulars
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateAndTime(): String {
        val sdf = SimpleDateFormat("dd/M/y hh:m aa")
        val calender = Calendar.getInstance()
        return sdf.format(calender.time)
    }


    fun setPatient(particulars: Particulars){
  //    particularsList =particulars
    }


    /*fun setParticulars(){
        patientsList?.value = listOfPatientPaticulars
    }*/

    fun getConsultationQue(particulars: Particulars){
  //     _patientsList.add(particulars)
    }

    fun getPatient():MutableList<Particulars>{
        return patientsList
    }

  fun setBioData(bio:Particulars){
   _bioList.add(bio)
      _queuedForConsultation.add(bio)
  }

  fun getBioData():MutableList<Particulars>
  {
    return bioList
  }

    fun setQueuedForConsultation (particulars: Particulars){
        _queuedForConsultation.add(particulars)
    }
    fun getQueuedForConsultation():MutableList<Particulars>
    {
        return queuedForConsultation
    }
    fun removeFromQue(particulars: Particulars){
        _queuedForConsultation.remove(particulars)
    }
    fun clearConsultationQue(){
        _queuedForConsultation.clear()
    }

    fun setQueuedForVitals(particulars: Particulars){
        _queueForVitals.add(particulars)
    }
    fun getQueuedForVitals():MutableList<Particulars>{
        return queuedForVitals
    }
    fun getCurrentVitalQueue(position: Int): Particulars {
        return getQueuedForVitals()[position]
    }
    fun removeFromVitalsQue(particular:Particulars){
        _queueForVitals.remove(particular)
    }
    fun clearVitalsList(){
        _queueForVitals.clear()
    }

    fun getPatientDetails(patientNumber: Int): Particulars {
        return getBioData()[patientNumber]
    }

    fun generateSerial(): Int{

        return (10000000..999999999).random()
    }

}
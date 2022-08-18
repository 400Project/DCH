package com.oyatech.dch.patient

import android.os.Build
import androidx.annotation.RequiresApi

import androidx.lifecycle.ViewModel
import com.oyatech.dch.model.PatientBioData
import com.oyatech.dch.model.listOfPatientPaticulars
import java.text.SimpleDateFormat
import java.util.*

class RegisterNewPatientViewModel : ViewModel() {
  //  private val listOfParticulars = PatientRepository(listOfPatientPaticulars)

    companion object{
        val viewModel = RegisterNewPatientViewModel()
        private val patientsList : MutableList<PatientBioData> = mutableListOf()

    }

private  var _bioList :MutableList<PatientBioData> = listOfPatientPaticulars
private val bioList = _bioList

    private var _queuedForConsultation:MutableList<PatientBioData> = mutableListOf()
    private  val queuedForConsultation = _queuedForConsultation

    private var _queueForVitals :MutableList<PatientBioData> = mutableListOf()
    private val queuedForVitals = _queueForVitals

    var registerViewMode :RegisterNewPatientViewModel? =null


  //  val patientList: LiveData<List<PatientBioData>> = liveData { _patientsList }

    //val allPatient = listOfPatientPaticulars
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateAndTime(): String {
        val sdf = SimpleDateFormat("dd/M/y hh:m aa")
        val calender = Calendar.getInstance()
        return sdf.format(calender.time)
    }


    fun setPatient(patientBioData: PatientBioData){
  //    particularsList =patientBioData
    }


    /*fun setPatientBioData(){
        patientsList?.value = listOfPatientPaticulars
    }*/

    fun getConsultationQue(patientBioData: PatientBioData){
  //     _patientsList.add(patientBioData)
    }

    fun getPatient():MutableList<PatientBioData>{
        return patientsList
    }

  fun setBioData(bio: PatientBioData){
   _bioList.add(bio)
      _queueForVitals.add(bio)
  }

  fun getBioData():MutableList<PatientBioData>
  {
    return bioList
  }

    fun setQueuedForConsultation (patientBioData: PatientBioData){
        _queuedForConsultation.add(patientBioData)
    }
    fun getQueuedForConsultation():MutableList<PatientBioData>
    {
        return queuedForConsultation
    }
    fun getCurrentQueuedForConsultation(position:Int): PatientBioData {
        return queuedForConsultation[position]
    }
    fun removeFromQue(patientBioData: PatientBioData){
        _queuedForConsultation.remove(patientBioData)
    }
    fun clearConsultationQue(){
        _queuedForConsultation.clear()
    }

    fun setQueuedForVitals(patientBioData: PatientBioData){
        _queueForVitals.add(patientBioData)
    }
    fun getQueuedForVitals():MutableList<PatientBioData>{
        return queuedForVitals
    }
    fun getCurrentVitalQueue(position: Int): PatientBioData {
        return getQueuedForVitals()[position]
    }
    fun removeFromVitalsQue(particular: PatientBioData){
        _queueForVitals.remove(particular)
    }
    fun clearVitalsList(){
        _queueForVitals.clear()
    }

    fun getPatientDetails(patientNumber: Int): PatientBioData {
        return getBioData()[patientNumber]
    }

    fun generateSerial(): Int{

        return (10000000..999999999).random()
    }

}
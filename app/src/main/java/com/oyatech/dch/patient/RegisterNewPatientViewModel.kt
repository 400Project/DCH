package com.oyatech.dch.patient

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oyatech.dch.model.DataSource
import com.oyatech.dch.model.DataSource.listOfPatientPaticulars
import com.oyatech.dch.model.PatientBioData
import com.oyatech.dch.observer.IOObservable
import com.oyatech.dch.vitals.VitalsViewModel
import java.text.SimpleDateFormat
import java.util.*

class RegisterNewPatientViewModel : ViewModel(), IOObservable {
    companion object {
        val viewModel = RegisterNewPatientViewModel()
    }


    private var _bioList = MutableLiveData<MutableList<PatientBioData>>()

    val bioList: LiveData<MutableList<PatientBioData>> get() = _bioList

    var patient = mutableListOf<PatientBioData>()

    private var _queuedForConsultation: MutableList<PatientBioData> = mutableListOf()
    private val queuedForConsultation = _queuedForConsultation

    private var _queueForVitals: MutableList<PatientBioData> = mutableListOf()
    private val queuedForVitals = _queueForVitals

    //val allPatient = listOfPatientPaticulars
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateAndTime(): String {
        val sdf = SimpleDateFormat("dd/M/y hh:m aa")
        val calender = Calendar.getInstance()
        return sdf.format(calender.time)
    }

    public fun dataInitializer() {
        _bioList.value = listOfPatientPaticulars
    }

    fun setBioData(bio: PatientBioData) {
        listOfPatientPaticulars.add(bio)
    }

    fun getBioData(): MutableList<PatientBioData> {
        return listOfPatientPaticulars
    }

    fun getQueuedForConsultation(): MutableList<PatientBioData> {
        return queuedForConsultation
    }

    fun getCurrentQueuedForConsultation(position: Int): PatientBioData {
        return queuedForConsultation[position]
    }

    fun getQueuedForVitals(): MutableList<PatientBioData> {
        return queuedForVitals
    }

    fun searchForPatient(query: String): LiveData<MutableList<PatientBioData>> {
        val list = MutableLiveData<MutableList<PatientBioData>>()
        val arrLis = mutableListOf<PatientBioData>()
        //Clearing the exiting list of patients
        DataSource.patient.clear()
        listOfPatientPaticulars.forEach {
            if (it.first_Name.contains(query, true)) {
                arrLis.add(it)
                //Assigning a list to live data object
                list.value = arrLis
                //adding the found list to the data source
                DataSource.patient.add(it)
            }
        }
        return list
    }


    override val vitalOpd: MutableList<VitalsViewModel>
        get() = TODO("Not yet implemented")


}
package com.oyatech.dch.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.DiagID
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.Vitals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicalHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private var _repository: Repository? = null
    private val repository get() = _repository!!


    private var _position:Int? =null
    val position get() = _position!!

    private var _allDiagnosis: LiveData<MutableList<Diagnose>>? = null
    val allDiagnosis: LiveData<MutableList<Diagnose>> get() = _allDiagnosis!!

    private var _listOfVitals: MutableList<Vitals> = mutableListOf()
    val listOfVitals: MutableList<Vitals> get() = _listOfVitals


  private  var _currentVitals: LiveData<MutableList<Vitals>>? = null
    val currentVitals: LiveData<MutableList<Vitals>> get()  = _currentVitals!!

    init {
        _repository = Repository(application)
    }

    fun setPosition(pos: Int) {
        _position = pos
    }

    //Diagnose table id
    fun insertDiagnoseIDs(diagID: DiagID) {
        viewModelScope.launch {
            repository.insertDiagnoseIDs(diagID)
        }
    }

    fun updateDiagnoseIDs(prev: Int, current: Int) {
        viewModelScope.launch {
            repository.updateDiagnoseIDs(prev, current)
        }
    }

    fun getDiagnoseIDs(): Int {
        return repository.getDiagnoseIDs()
    }


    fun insertDiagnoseRemote(diagnose: Diagnose) {
        repository.insertDiagnosisRemote(diagnose)
    }

    fun fetchAllVitals(patientId: Int): LiveData<MutableList<Vitals>> {

        viewModelScope.launch {
          Dispatchers.IO
            _currentVitals = repository.fetchAllVitals(patientId)
        }
        return currentVitals
    }

    //remove patient from consultation queue
    fun removeConsultation(int: Int) {
        viewModelScope.launch {
            Dispatchers.Default
            repository.removeConsultation(int)
        }

    }

    fun fetchDiagnosis(patientId: Int)
            : LiveData<MutableList<Diagnose>> {
        viewModelScope.launch {
            Dispatchers.IO
            _allDiagnosis = repository.fetchDiagnosis(patientId)
        }
        return allDiagnosis
    }

    fun getVitals():MutableList<Vitals>{
        currentVitals.value?.forEach {
            _listOfVitals.add(it)
        }
        return listOfVitals
    }
}
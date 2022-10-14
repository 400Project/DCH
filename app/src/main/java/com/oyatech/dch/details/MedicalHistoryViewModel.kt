package com.oyatech.dch.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.IDiagnoseId
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.DiagID
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.Dispensary
import com.oyatech.dch.database.entities.Vitals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MedicalHistoryViewModel(application: Application) : AndroidViewModel(application),
    IDiagnoseId {

    private var _repository: Repository? = null
    private val repository get() = _repository!!


    private var _position: Int? = null
    val position get() = _position!!

    private var _allDiagnosis: LiveData<MutableList<Diagnose>>? = null
    private val allDiagnosis: LiveData<MutableList<Diagnose>> get() = _allDiagnosis!!

    private var _listOfVitals: TreeMap<Int, Vitals> = TreeMap()
    val listOfVitals: TreeMap<Int, Vitals> get() = _listOfVitals


    private var _currentVitals: LiveData<MutableList<Vitals>>? = MutableLiveData()
    val currentVitals: LiveData<MutableList<Vitals>> get() = _currentVitals!!

    private var _diagnoses: TreeMap<Int, Diagnose> = TreeMap()
    val diagnoses: TreeMap<Int, Diagnose> get() = _diagnoses

    private var _dipensery: Dispensary? = null
     val dipensery: Dispensary get()  = _dipensery!!

    init {
        _repository = Repository(application)
    }

    fun setPosition(pos: Int) {
        _position = pos
    }


    fun insertDiagnoseRemote(diagnose: Diagnose) {
        viewModelScope.launch {
            repository.insertDiagnosisRemote(diagnose)
        }
    }

    fun fetchAllVitals(patientId: Int): LiveData<MutableList<Vitals>> {

        viewModelScope.launch {
            Dispatchers.Default
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
            Dispatchers.Default
            _allDiagnosis = repository.fetchDiagnosis(patientId)
        }

        return allDiagnosis
    }

    fun setVitals() {
        viewModelScope.launch {
            Dispatchers.Default
            _currentVitals?.value?.forEach {
                _listOfVitals[it.vitalsID] = it
            }
        }
    }

    fun setDiagnosis() {
        viewModelScope.launch {
            Dispatchers.Default
            _allDiagnosis?.value?.forEach {
                _diagnoses[it.diagnoseID] = it
            }
        }
    }

    override fun insertDiagnoseId(id: DiagID) {
        viewModelScope.launch {
            repository.insertDiagnoseId(id)
        }
    }

    override fun getDiagnoseId(): LiveData<Int> {
        return repository.getDiagnoseId()
    }


}
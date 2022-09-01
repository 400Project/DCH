package com.oyatech.dch.consultations

import android.app.Application
import androidx.lifecycle.*
import com.oyatech.dch.database.IConsult
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.Vitals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConsultationViewModel(application: Application): AndroidViewModel(application)
    ,IConsult {

    private var _repository : Repository? = null
    private val repository get()=_repository!!

private var _position = 0
    val position get() = _position

    init {
        _repository = Repository(application)
    }

    override   fun bookForConsultation(bioData: DailyConsultation){
        viewModelScope.launch {
            Dispatchers.IO
            repository.bookForConsultation(bioData)
        }
    }

    override   fun getAllBookedForConsultation():LiveData<MutableList<DailyConsultation>>{

        return repository.getAllBookedForConsultation()
    }

    override fun getDailConsultationByID(id: Int): DailyConsultation {
        return repository.getDailConsultationByID(id)
    }
    override fun getCurrentPatientAtConsultation(id: Int): PatientBioData {
        return repository.getCurrentPatientAtConsultation(id)
    }

    override fun getCurrentVitals(id: Int): Vitals {
        return repository.getCurrentVitals(id)
    }



    override fun getAllPatientDiagnoses(foreignKey: Int): LiveData<MutableList<Diagnose>> {
        var diagnose:LiveData<MutableList<Diagnose>> = MutableLiveData()
        viewModelScope.launch {
            Dispatchers.Default
            diagnose = repository.getAllPatientDiagnoses(foreignKey)
        }
        return diagnose

    }

    override fun insertDiagnosis(diagnose: Diagnose) {
        repository.insertDiagnosis(diagnose)
    }


    fun setPosition(position:Int)
    {
        _position = position

    }


}
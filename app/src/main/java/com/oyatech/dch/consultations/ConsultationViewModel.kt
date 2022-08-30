package com.oyatech.dch.consultations

import android.app.Application
import androidx.lifecycle.*
import com.oyatech.dch.database.IConsult
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.model.DataSource
import com.oyatech.dch.model.DataSource.consultQueue
import com.oyatech.dch.database.entities.PatientBioData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConsultationViewModel(application: Application): AndroidViewModel(application),IConsult {

    private var _repository : Repository? = null
    private val repository get()=_repository!!



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

    override fun getCurrentPatientForConsult(id: Int): PatientBioData {
        return repository.getCurrentPatientForVitals(id)
    }
}
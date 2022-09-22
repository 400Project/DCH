package com.oyatech.dch.consultations

import android.app.Application
import androidx.lifecycle.*
import com.oyatech.dch.database.IConsult
import com.oyatech.dch.database.Repository
import com.oyatech.dch.database.entities.*
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

    override fun removeConsultation(int: Int) {
        TODO("Not yet implemented")
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


    fun setPosition(position:Int)
    {
        _position = position

    }
    //Remote operations
    fun fetchDailyConsultRemote(): LiveData<MutableList<DailyConsultation>>{
        var allRecords:LiveData<MutableList<DailyConsultation>> = MutableLiveData()
        viewModelScope.launch { Dispatchers.IO
            allRecords =  repository.fetchDailyConsult()

        }
        return allRecords
    }



}
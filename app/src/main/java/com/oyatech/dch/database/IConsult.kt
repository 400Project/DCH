package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.database.entities.PatientBioData

interface IConsult {
    fun bookForConsultation(bioData: DailyConsultation )

    fun getAllBookedForConsultation(): LiveData<MutableList<DailyConsultation>>

    fun getCurrentPatientForConsult(id:Int): PatientBioData
}
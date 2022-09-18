package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.DailyConsultation
import com.oyatech.dch.database.entities.Diagnose
import com.oyatech.dch.database.entities.PatientBioData
import com.oyatech.dch.database.entities.Vitals

interface IConsult {
    fun bookForConsultation(bioData: DailyConsultation )

    fun getAllBookedForConsultation(): LiveData<MutableList<DailyConsultation>>

    fun removeConsultation(int: Int)
    fun getDailConsultationByID(id:Int): DailyConsultation

    fun  getCurrentVitals(id:Int):Vitals
    fun getCurrentPatientAtConsultation(id:Int):PatientBioData



}
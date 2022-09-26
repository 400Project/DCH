package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.ViDs

interface IVitalsId {
    fun insertVitalId(id: ViDs)
    fun getVitalsId():LiveData<Int>
}
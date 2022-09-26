package com.oyatech.dch.database

import androidx.lifecycle.LiveData
import com.oyatech.dch.database.entities.DiagID

interface IDiagnoseId {

    fun insertDiagnoseId(id: DiagID)
    fun getDiagnoseId():LiveData<Int>
}
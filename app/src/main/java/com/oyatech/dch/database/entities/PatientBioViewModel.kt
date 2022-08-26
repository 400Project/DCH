package com.oyatech.dch.database.entities

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oyatech.dch.database.DCHDatabase.Companion.getDatabaseInstance
import com.oyatech.dch.database.Repository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class PatientBioViewModel(application: Application) : AndroidViewModel(application) {

    private var repository :Repository? = null

    init {
        repository = Repository(application)
    }

    fun insertBioData(patientBioData: PatientBioData){
        viewModelScope.launch { Dispatchers.IO
            repository?.insertPatientBio(patientBioData)
        }
    }


}
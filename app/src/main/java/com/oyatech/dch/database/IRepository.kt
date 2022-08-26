package com.oyatech.dch.database

import com.oyatech.dch.database.entities.PatientBioData

interface IRepository {
    fun insertPatientBio(patientBioData: PatientBioData)
}
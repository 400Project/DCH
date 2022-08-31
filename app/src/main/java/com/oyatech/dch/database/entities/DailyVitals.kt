package com.oyatech.dch.database.entities

import androidx.room.*

@Entity(indices = [Index (value = ["patientId","patient_Hosp_Number"], unique = true)])
data  class DailyVitals (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

   @Embedded
   val patientBioData: PatientBioData
)

package com.oyatech.dch.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data  class DailyVitals (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

   @Embedded
   val patientBioData: PatientBioData
)

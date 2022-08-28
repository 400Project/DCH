package com.oyatech.dch.database.entities

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Relation

data class PatientVitals(
    @NonNull
    @Embedded val patient: PatientBioData,
    @Relation(
        //Primary Key of the patient bio data
        parentColumn = "patientId",
        //Foreign key name in the vital entity
        entityColumn = "patientFK"
    )
    @NonNull
    val dailyVitals: DailyVitals
)

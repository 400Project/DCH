package com.oyatech.dch.database.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class Vitals(
    @PrimaryKey(autoGenerate = true) val vID: Int,
    //Foreign Key
    val patientId: Int,
    //Foreign Key
    val staffID:Int,

    @ColumnInfo(name = "date_recorded")
    val date: String,
    val time: String,
    val bloodPressure: String,
    val weight: String,
    val bodyTemperature: String
)

@Entity
data class PatientVitals(
    @Embedded val patientBioData: PatientBioData,
@Relation
    (
    parentColumn = "patientId",
    entityColumn = "vitalsID")
val allVitals: LiveData<MutableList<Vitals>>

)
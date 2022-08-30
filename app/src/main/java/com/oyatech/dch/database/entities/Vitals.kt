package com.oyatech.dch.database.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oyatech.dch.util.Utils

@Entity
data class Vitals(
    @PrimaryKey(autoGenerate = true) val vID: Int,
    //Foreign Key
    val foreignKyePatient: Int,

    @ColumnInfo(name = "date_recorded")
    val bloodPressure: String,
    val weight: String,
    val bodyTemperature: String,
    val sugarLevel:String,
    val dateTime: String = Utils.getDateAndTime()
)

//Modeling one-to-many relationship between patient and vitals
@Entity
data class BioAndVitals(
    @Embedded val patientBioData: PatientBioData,
@Relation
    (
    //Primary Key of the patient bio data
    parentColumn = "patientId",
    //Foreign key name in the vital entity
    entityColumn = "foreignKyePatient")
val allVitals: LiveData<MutableList<Vitals>>

)
package com.oyatech.dch.database.entities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.room.*
import com.oyatech.dch.util.Utils

@Entity
data class Vitals constructor(
    @PrimaryKey(autoGenerate = true)
    val vitalsID: Int,
    //Foreign Key
    val patientId: Int,

    val bloodPressure: String,
    val weight: String,
    val bodyTemperature: String,
    val sugarLevel:String,
    val date: String = Utils.getDate(),
    val time: String = Utils.getTime(),
    val recordBy:String = "Grace Ama"
){
    constructor():this(0,0,"","","","","","","")
}

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
package com.oyatech.dch.model

import androidx.annotation.Nullable
import androidx.room.Entity
import java.io.Serializable
import java.util.*


data class PatientBioData(
    val hospitalNumber:String,
    var first_Name: String,
    var otherNames: String,
    var address:String,
    val dob: String,
    val sex: String,
    var occupation:String,
    var date: String,
    var mobile:String,
    var insuranceNumber:String,
    var age:String,
    val recordedBys: String
)
{
    constructor():this(
        "DCH/22/22",
        " Dr. Asong",
        "Dzuma",
        "Kwadaso",
        "02/04/2022",
        "M",
        "Teaching",
        "1/8/2022",
    "0244344443",
    "22324456",
    "1year",
    "Mercy")
}

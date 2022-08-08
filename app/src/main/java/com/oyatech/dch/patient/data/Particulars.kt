package com.oyatech.dch.patient.data

import androidx.annotation.Nullable
import java.io.Serializable
import java.util.*

data class Particulars(
val hospitalNumber:String,
    var firstName: String,
    var otherNames: String,
    var address:String,
    val dob: String,
    val sex: String,
    var occupation:String,
    var date: String,
    var mobile:String,
    var insuranceNumber:String,
var age:String
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
    "1year")
}

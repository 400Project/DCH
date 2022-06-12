package com.oyatech.dch.patient.data

import androidx.annotation.Nullable
import java.io.Serializable
import java.util.*

data class Particulars(
    val firstName: String,
    val otherNames: String,
    val dateAndTime: String
){

/* @Nullable val sex:String,
    @Nullable val dateOfBirth: String,
    @Nullable val occupation: String,
    @Nullable val mobile: String,
    @Nullable val insuranceNumber:String,
    @Nullable val folderNumber:String,
    @Nullable val vitals: Vitals?*/
}

package com.oyatech.dch.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NextOfKin(
     val nextOfKinID:Int?,
    val patientId:Int?,
    val fullName:String?,
    val mobile:String?,
    val relationship:String?,
    val address: String?,
    val occupation:String?
)

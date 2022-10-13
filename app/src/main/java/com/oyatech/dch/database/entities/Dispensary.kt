package com.oyatech.dch.database.entities

data class Dispensary(
    val dispenseId: Int,
    //Foreign Key
    val staffName: String?,
    val patientFName:String?,
    val patientOtherName:String?,
    val prescription: String?,
    val hospitalNumber: String?,
    val date:String?,
    val time: String?
)

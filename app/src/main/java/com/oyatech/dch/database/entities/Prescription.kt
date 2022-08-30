package com.oyatech.dch.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Prescription(
    @PrimaryKey(autoGenerate = true) val presID: Int,
    //Foreign Key
    val diagnoseID: Int,

    val dispenID: Int,

    var prescriptions: String
)


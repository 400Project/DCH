package com.oyatech.dch.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Dispensary(
    @PrimaryKey(autoGenerate = true) val dispenID: Int,
    //Foreign Key
    val staffID: Int,

    val date:String,
    val time: String
)

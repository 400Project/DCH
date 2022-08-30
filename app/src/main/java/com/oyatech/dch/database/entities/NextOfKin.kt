package com.oyatech.dch.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NextOfKin(
    @PrimaryKey(autoGenerate = true) val nextOfKin:Int,

    var fullName:String,
    var mobile:String?,
    var relationship:String,
    var address: String?,
    var occupation:String?
)

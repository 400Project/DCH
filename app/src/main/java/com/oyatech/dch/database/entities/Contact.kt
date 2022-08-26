package com.oyatech.dch.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val cID: Int,
    //Foreign Key
    val staffID: Int,

    @ColumnInfo(name = "Patient_Contacts")
    var contact: String
)

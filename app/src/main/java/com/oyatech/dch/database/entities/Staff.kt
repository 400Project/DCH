package com.oyatech.dch.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Staff(
    @PrimaryKey(autoGenerate = true) val staffID: Int,
    //Foreign Key (department)
    @ColumnInfo(name = "Department_ID")
    val departID:Int,

    val firstName: String,
    val otherNames: String,
    var address: String,
    @ColumnInfo(name = "date_Of_Birth")
    val dob: String,
    val sex: String,
    @ColumnInfo(name = "date_Hired")
    val date: String,
    val staffNumber: String,
    var qualification: String
)

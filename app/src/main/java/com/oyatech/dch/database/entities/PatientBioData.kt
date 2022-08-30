package com.oyatech.dch.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PatientBioData(
    @PrimaryKey(autoGenerate = true)
    val patientId: Int,
  /*  //Foreign Key
    val nextOfKin:Int,

    val staffID: Int,*/

    @ColumnInfo(name = "patient_Hosp_Number")
    val hospitalNumber: String,
    var first_Name: String,
    var otherNames: String,
    var address: String,
    @ColumnInfo(name = "date_Of_Birth")
    val dob: String,
    @ColumnInfo(name = "gender")
    val sex: String,
    var occupation: String,
    @ColumnInfo(name = "dateOfFirstVisit")
    var date: String,
    var mobile: String,
    var insuranceNumber: String,
    var age: String,
) {

    override fun toString(): String {
        return super.toString()
    }
}
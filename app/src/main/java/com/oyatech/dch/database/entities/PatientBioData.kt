package com.oyatech.dch.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oyatech.dch.util.Utils

@Entity
data class PatientBioData(
    @PrimaryKey(autoGenerate = true)
    val patientId: Int= Utils.patientKay,
  /*  //Foreign Key
    val nextOfKin:Int,

    val staffID: Int,*/
    val hospitalNumber: String,
    var first_Name: String,
    var otherNames: String,
    var address: String,
    @ColumnInfo(name = "date_Of_Birth")
    val dob: String,
    var age: String,
    val gender: String,
    var occupation: String,
    var mobile: String,
    var insuranceNumber: String,
    @ColumnInfo(name = "dateOfFirstVisit")
    var dates: String,
    val times:String
    ){

constructor():this(0,"","","","","","","","","","","","")


    override fun toString(): String {
        return super.toString()
    }
}
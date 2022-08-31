package com.oyatech.dch.database.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oyatech.dch.util.Utils

@Entity
data class Diagnose(
    @PrimaryKey(autoGenerate = true) val diagnoseID: Int,
    //Foreign Key
    val parentID:Int,

    var provisional: String,
    @ColumnInfo(name = "Tests_Conducted")
    var tests: String,
    var principal: String,
    var additional: String,
    var treatmentStatus: String,
    val staffName:String,

    val dateTime :String = Utils.getDateAndTime()
)

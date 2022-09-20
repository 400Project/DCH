package com.oyatech.dch.database.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oyatech.dch.util.Utils

@Entity
data class Diagnose(
    @PrimaryKey(autoGenerate = true)
    val diagnoseID: Int,
    //Foreign Key
    val patientId: Int,
    //foreign key
    val vitalsID: Int,

    var provisional: String,
    var principal: String,
    var additional: String,
    var prescription: String,
    var nurseNote: String,
    val staffName: String,
    var treatmentStatus: String,
    var labs: String = "Not Lab",
    val date: String = Utils.getDate(),
val time: String = Utils.getTime()
) {
    constructor() : this(0, 0, 0,
        "", "", "", "",
        "", "", "","","","")
}

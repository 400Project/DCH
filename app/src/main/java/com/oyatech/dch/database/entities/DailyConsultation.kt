package com.oyatech.dch.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.oyatech.dch.util.Utils

//Making the table have a unique objects
@Entity(indices = [Index(value = ["patientId","hospitalNumber"], unique = true)])
data class DailyConsultation(
    @PrimaryKey(autoGenerate = true)
    val consultID: Int,
    @Embedded
    val bios: PatientBioData,
    val dataTime: String =Utils.getDate()
)
{
    constructor():this(0,PatientBioData())
}
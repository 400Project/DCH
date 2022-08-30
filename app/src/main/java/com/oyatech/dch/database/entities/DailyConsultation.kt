package com.oyatech.dch.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oyatech.dch.util.Utils

@Entity
data class DailyConsultation(
    @PrimaryKey(autoGenerate = true) val consultID: Int,
    @Embedded
    val bios: PatientBioData,
    val dataTime: String =Utils.getDateAndTime()
)

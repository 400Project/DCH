package com.oyatech.dch.database

import androidx.room.Dao
import androidx.room.Insert
import com.oyatech.dch.database.entities.PatientBioData

@Dao
interface IDao {
    @Insert
    fun insertBioData(bioData: PatientBioData)
}
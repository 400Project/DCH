package com.oyatech.dch.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiagID(
    @PrimaryKey
    var diagnoseID:Int =0 )
package com.oyatech.dch.database.entities

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Department(
    @PrimaryKey(autoGenerate = true) val departID:Int,
    var name:String,
    @Nullable
    var head:String?
)

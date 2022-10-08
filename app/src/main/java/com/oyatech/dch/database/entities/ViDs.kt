package com.oyatech.dch.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oyatech.dch.util.Utils

@Entity
data class ViDs(
    @PrimaryKey
    var vId:Int =0 )

package com.oyatech.dch.model

import androidx.room.Entity
import java.util.*

@Entity
class Diagnose(){
    constructor(provisional:String,
                tests: ArrayList<String>,
                principal:String,
                additional: String):this()
}


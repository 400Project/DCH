package com.oyatech.dch.model

import androidx.room.Entity


data class MedicalHistory(var vitals: Vitals){
  lateinit  var diagnose: Diagnose
  lateinit  var prescription: Prescription

}

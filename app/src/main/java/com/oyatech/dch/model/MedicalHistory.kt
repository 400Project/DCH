package com.oyatech.dch.model

data class MedicalHistory(var vitals: Vitals){
  lateinit  var diagnose: Diagnose
  lateinit  var prescription: Prescription

}

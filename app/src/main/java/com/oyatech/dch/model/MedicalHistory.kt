package com.oyatech.dch.model

import androidx.room.Entity



data class MedicalHistory(
  var vitals: Vitals,
  var diagnose: Diagnose,
)

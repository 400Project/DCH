package com.oyatech.dch.model

import androidx.room.Entity

data class Vitals(
    val dateTime: String,
    val bloodPressure: String,
    val weight: String,
    val bodyTemperature: String,
    val recordedBy: String
) {
    class Vitals

    override fun toString(): String {
        return "Vitals Taken as Of $dateTime" +
                "\n-------------------------\n" +
                "Blood Pressure:  $bloodPressure\n" +
                "Body Weight:     $weight\n" +
                "Body Temperature:$bodyTemperature"
    }


}



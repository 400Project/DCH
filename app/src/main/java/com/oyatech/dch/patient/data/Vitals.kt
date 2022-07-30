package com.oyatech.dch.patient.data

data class Vitals(
    val dateTime: String,
    val bloodPressure: String,
    val weight: String,
    val bodyTemperature: String
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



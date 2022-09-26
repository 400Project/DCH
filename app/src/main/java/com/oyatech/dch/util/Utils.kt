package com.oyatech.dch.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    val calender = Calendar.getInstance()

    var patientKay: Int = 0

    fun getDate(): String {
        val locale = Locale("en", "gh")
        val sdf = SimpleDateFormat("dd/M/yyyy", locale)
        return sdf.format(calender.time)
    }

    fun getTime(): String {
// Getting the current current time
        // Getting the current current time
        val date = Date()

        // set format in 12 hours
        val formatTime = SimpleDateFormat("h:mm aa")

        val time = formatTime.format(
            date
        ) // changing the format of 'date'

        return time
    }

    fun generateHospitalNumber(primaryKey: Int): String {
        val year = calender.get(Calendar.YEAR)


        return "DCH/${primaryKey}/$year"
    }


}

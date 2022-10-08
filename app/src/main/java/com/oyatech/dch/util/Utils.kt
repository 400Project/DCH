package com.oyatech.dch.util

import android.app.DatePickerDialog
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.oyatech.dch.database.entities.Department
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.sqrt

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

    fun Context.pickDate() :String{
        var monthOfBirth = 0
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(applicationContext,
            { datePicker, year, month, day ->
                monthOfBirth = month +1
            },year,month,day).show()
return "\"$day/$monthOfBirth/$year\""
    }

    fun staffID(department: String):String{
        val random1: Int = (2.. 10).random()
        val random2: Int = (3.. 15).random()
        val random = (1..50).random().div(random1) * random2
        val dp = department.substring(0,3)
        return "DCH.$dp.$random"
    }
}

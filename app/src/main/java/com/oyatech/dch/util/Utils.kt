package com.oyatech.dch.util

import android.icu.text.TimeZoneFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

object Utils {
    val calender = Calendar.getInstance()

   var patientKay:Int =0
    var previous = 0

  private  var qVitalsKey = 0
 private   var qPreviousKey =0

    fun getDate(): String {
        val sdf = SimpleDateFormat("dd/M/y")
        return sdf.format(calender.time)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTime():String{
val time = LocalTime.now()
   val dtf =     DateTimeFormatter.ofPattern("HH:mm:ss")

        return time.format(dtf)
    }

    fun generateHospitalNumber (primaryKey:Int):String{
        val year = calender.get(Calendar.YEAR)


        return  "DCH/${primaryKey}/$year"
    }
    fun dailyVitalKey ():Int{

        qVitalsKey = (1..100).shuffled().last()

        if (qVitalsKey== qPreviousKey){
            dailyVitalKey()
        }else
            qPreviousKey = qVitalsKey

        return qVitalsKey
    }
}

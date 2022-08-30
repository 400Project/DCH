package com.oyatech.dch.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getDateAndTime(): String {
        val l = Locale.getDefault()
        val sdf = SimpleDateFormat("dd/M/y hh:m aa",l)
        val calender = Calendar.getInstance()
        return sdf.format(calender.time)
    }
}

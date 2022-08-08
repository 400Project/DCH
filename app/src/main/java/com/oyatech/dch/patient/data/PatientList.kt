package com.oyatech.dch.patient.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



     val   listOfPatientPaticulars= mutableListOf<Particulars>()/*mutableListOf(
            Particulars("Robert","Oyadier","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Owusu","Bernard","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Noah","Narh","Accra","02/3/1987","M","Teaching","1/08/2022"),

            Particulars("Noah","Adjetey","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Achiampong","Daniel","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Bright","Aduve","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Eric","Anane","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Robert","Addotso","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Sarah","Perez","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Noah","Narh","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Helen","Amidini","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Louis","Afriyie","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Adjimajor","Abraham","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Assiangmor","Issah","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Faustina","Narko","Accra","02/3/1987","M","Teaching","1/08/2022"),
            Particulars("Rebecca","Teye","Accra","02/3/1987","M","Teaching","1/08/2022"))
*/
  fun  getInstance(){

 }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateAndTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"))
    }


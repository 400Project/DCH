package com.oyatech.dch.patient.data

data class Paticulars(val firstName:String,
                      val otherNames:String){

    var fName = firstName.uppercase()
    set(value){
        if (value.isNotEmpty()) field = firstName
    }

}

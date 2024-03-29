package com.oyatech.dch.model

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DataSource {

   var listOfPatientPaticulars = mutableListOf(
        PatientBioData(
            "DCH/2/2021",
            "Robert",
            "Oyadier",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Suleman Mohammed"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Owusu",
            "Bernard",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2012",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Noah",
            "Narh",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),

        PatientBioData(
            "DCH/2/2021",
            "Noah",
            "Adjetey",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/09/22",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Achiampong",
            "Daniel",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "11/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Bright",
            "Aduve",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "14/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Angela Addo"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Eric",
            "Anane",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "13/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Kwame Antwi"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Robert",
            "Addotso",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "8/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Micheal Addo"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Sarah",
            "Perez",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "7/08/202",
            "0232234322",
            "11233233",
            "34yrs",
            "Nortey Adjei"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Bright",
            "Aduve",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "14/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Angela Addo"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Eric",
            "Anane",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "13/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Kwame Antwi"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Robert",
            "Addotso",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "8/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Micheal Addo"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Sarah",
            "Perez",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "7/08/202",
            "0232234322",
            "11233233",
            "34yrs",
            "Nortey Adjei"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Noah",
            "Narh",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "11/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bernard Akli"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Noah",
            "Narh",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "11/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bernard Akli"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Helen",
            "Amidini",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bernard Akli"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Louis",
            "Afriyie",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "21/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bernard Akli"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Adjimajor",
            "Abraham",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2002",
            "Assiangmor",
            "Issah",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/202",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Faustina",
            "Narko",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Rebecca",
            "Teye",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Robert",
            "Oyadier",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Suleman Mohammed"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Owusu",
            "Bernard",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2012",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Noah",
            "Narh",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),

        PatientBioData(
            "DCH/2/2021",
            "Noah",
            "Adjetey",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/09/22",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Achiampong",
            "Daniel",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "11/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),

        PatientBioData(
            "DCH/2/2021",
            "Helen",
            "Amidini",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bernard Akli"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Louis",
            "Afriyie",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "21/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bernard Akli"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Adjimajor",
            "Abraham",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2002",
            "Assiangmor",
            "Issah",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/202",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Faustina",
            "Narko",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        ),
        PatientBioData(
            "DCH/2/2021",
            "Rebecca",
            "Teye",
            "Accra",
            "02/3/1987",
            "M",
            "Teaching",
            "1/08/2022",
            "0232234322",
            "11233233",
            "34yrs",
            "Bright Narh"
        )
    )


    private val vitalQueue  = mutableListOf<PatientBioData>()
    private val consultation = mutableListOf<PatientBioData>()
    private val wardList = mutableListOf(PatientBioData(
        "DCH/2/2021",
        "Robert",
        "Oyadier",
        "Accra",
        "02/3/1987",
        "M",
        "Teaching",
        "1/08/2022",
        "0232234322",
        "11233233",
        "34yrs",
        "Suleman Mohammed"
    ))
   var patient = mutableListOf<PatientBioData>()

    fun addVitalQue(bioData: PatientBioData) {
        vitalQueue.add(bioData)
        Log.i(TAG, "addPatientBioData: ${vitalQueue.size}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateAndTime(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"))
    }

    fun allPatient(): MutableList<PatientBioData> {
        return vitalQueue
    }
    fun allWardPatient(): MutableList<PatientBioData> {
        return wardList
    }

    fun consultQueue(): MutableList<PatientBioData> {

        return consultation
    }

    fun addConsultation(patientBioData: PatientBioData){
        consultation.add(patientBioData)
    }

    fun searchPatient(index: Int):PatientBioData
    {
        if (patient.isEmpty()){
            patient.addAll(listOfPatientPaticulars)
        }
        return patient[index]
    }

}
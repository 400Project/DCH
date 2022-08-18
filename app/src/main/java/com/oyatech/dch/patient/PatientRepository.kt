package com.oyatech.dch.patient

import com.google.firebase.firestore.FirebaseFirestore
import com.oyatech.dch.model.PatientBioData

class PatientRepository(patientList: MutableList<PatientBioData>) {
    /*Repository class that will load and save patients records to the fireStore
    * It is a best practice to separate the data layer from the ui layer of the application thereby
    * ensure a clean code*/

//    val viewModel = RegisterNewPatientViewModel()
    private val listOfPatient :MutableList<PatientBioData> = patientList

    fun loadPatientFromStore():MutableList<PatientBioData>
    {
        return listOfPatient
    }

    fun  savePatientsListToStore(patientBioData: PatientBioData)
    {
        val fireStore = FirebaseFirestore.getInstance()
        val dchDatabase = fireStore.collection("DCH").add(patientBioData)
            .addOnSuccessListener {
               //Do some Toast
            }
            .addOnFailureListener {
                //Do some feedback Toast
            }
    }

    /**
     * TODO: BEGIN TO SAVE DOCUMENT TO CLOUD STORE
     */

}
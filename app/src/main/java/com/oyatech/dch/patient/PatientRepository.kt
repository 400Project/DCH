package com.oyatech.dch.patient

import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.oyatech.dch.patient.data.Particulars

class PatientRepository(patientList: MutableList<Particulars>) {
    /*Repository class that will load and save patients records to the fireStore
    * It is a best practice to separate the data layer from the ui layer of the application thereby
    * ensure a clean code*/

//    val viewModel = RegisterNewPatientViewModel()
    private val listOfPatient :MutableList<Particulars> = patientList

    fun loadPatientFromStore():MutableList<Particulars>
    {
        return listOfPatient
    }

    fun  savePatientsListToStore(particulars: Particulars)
    {
        val fireStore = FirebaseFirestore.getInstance()
        val dchDatabase = fireStore.collection("DCH").add(particulars)
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
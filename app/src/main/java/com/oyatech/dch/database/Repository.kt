package com.oyatech.dch.database


import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oyatech.dch.database.entities.*

typealias liveData = (LiveData<MutableList<Any>>)

class Repository(application: Application) : IRepository, IConsult {

    val TAG = Repository::class.java.simpleName
    private var _mao: IDao? = null
    private val mDao get() = _mao!!

    private val _allRecords: MutableLiveData<MutableList<PatientBioData>> = MutableLiveData()
    private val allRecords: LiveData<MutableList<PatientBioData>> = _allRecords!!

    private val _allVitals: MutableLiveData<MutableList<DailyVitals>>? = MutableLiveData()
    private val allVitals: LiveData<MutableList<DailyVitals>> = _allVitals!!

    private var _dayVitals: MutableLiveData<MutableList<Vitals>> = MutableLiveData()
    private val dayVitals: LiveData<MutableList<Vitals>> = _dayVitals!!

    private var _allDiagnosis: MutableLiveData<MutableList<Diagnose>> = MutableLiveData()
    private val allDiagnosis: LiveData<MutableList<Diagnose>> = _allDiagnosis

    private val firestore = Firebase.firestore
    private val DCH = "dch"
    private val VITALS = "Vitals"
    private val DAILY_VITALS = "dailyVitals"
    private val DAILY_CONSULT = "dailyConsult"
    private val DIADNOSIS = "diagnosis"

    init {
        val mIDao = DCHDatabase.getDatabaseInstance(application)
        _mao = mIDao.mDao()
    }


    /**
     * The Patient Bio data table for all records
     */
    override fun insertPatientBio(patientBioData: PatientBioData) {
        mDao.insertBioData(patientBioData)
    }

    override fun getAllBioData(): LiveData<MutableList<PatientBioData>> {

        return mDao.getAllBioData()
    }

    override fun currentBio(int: Int): PatientBioData {
        return mDao.allPatients(int)
    }

    override fun searchForPatient(search: String): LiveData<MutableList<PatientBioData>> {
        return mDao.searchForPatient(search)
    }

    /**
     * The Whole Vitals table that contains all vitals of the patient records
     */
    fun insertVitals(vitals: Vitals) {
        mDao.insertVitals(vitals)
    }

    //Vitals table id
    fun insertVitalsIDs(vitals: ViDs) {
        mDao.insertVitalsIDs(vitals)
    }

    fun updateVitalsIDs(prev: Int, current: Int) {
        mDao.updateVitalsIDs(prev, current)
    }

    fun getVitalsIDs(): Int {
        return mDao.getVitalsIDs()
    }

    //Diagnose table id
    fun insertDiagnoseIDs(diagID: DiagID) {
        mDao.insertDiagnoseIDs(diagID)
    }

    fun updateDiagnoseIDs(prev: Int, current: Int) {
        mDao.updateDiagnoseIDs(prev, current)
    }

    fun getDiagnoseIDs(): Int {
        return mDao.getDiagnoseIDs()
    }


    override fun getCurrentVitals(id: Int): Vitals {

        return mDao.getCurrentVitals(id).last()
    }

    fun getCurrentPatientForVitals(id: Int): PatientBioData {
        return mDao.getCurrentPatientForVitals(id).patientBioData
    }


    /**
     * Daily Vitals list.
     */
    override fun queueForVitals(dailyVitals: DailyVitals) {
        mDao.queueForVitals(dailyVitals)
    }

    /* override fun getQueueForVitals(): LiveData<MutableList<DailyVitals>> {

         return mDao.getQueueForVitals()
     }
 */

    fun getCurrentQueVitals(id: Int): DailyVitals {
        return mDao.getCurrentPatientForVitals(id)
    }


    /**
     * Daily Consultation table
     */
    override fun bookForConsultation(bioData: DailyConsultation) {
        mDao.bookForConsultation(bioData)
    }

    override fun getAllBookedForConsultation(): LiveData<MutableList<DailyConsultation>> {

        return mDao.getAllBookedForConsultation()
    }


    override fun getDailConsultationByID(id: Int): DailyConsultation {
        return mDao.getDailConsultationByID(id)
    }

    override fun getCurrentPatientAtConsultation(id: Int): PatientBioData {
        return mDao.getDailConsultationByID(id).bios
    }


    /**
     * The Diagnoses table for all diagnoses of all records
     */
    fun fetchDiagnosis(foreignKey: Int): LiveData<MutableList<Diagnose>> {
        var listOfRecords: MutableList<Diagnose> = mutableListOf()
        firestore.collection(DCH).document(foreignKey.toString())
            .collection(DIADNOSIS)
            .orderBy("date", Query.Direction.DESCENDING)
            .get().addOnSuccessListener {
                listOfRecords = it.toObjects(Diagnose::class.java)
                _allDiagnosis.value = listOfRecords
                Log.i(TAG, "get patient diagnosis: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "failed fetching diagnosis${it.message}")
            }
        return allDiagnosis
    }

    /**
     * Inserting patient diagnoses into the diagnose table online
     */
    fun insertDiagnosisRemote(diagnose: Diagnose) {
        firestore.collection(DCH).document(diagnose.parentID.toString())
            .collection(DIADNOSIS).document("${diagnose.diagnoseID}")
            .set(diagnose).addOnSuccessListener {
                Log.i(TAG, "insertDailyVitals: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "insertDailyVitals: ${it.message}")
            }
    }


    /*
        * The following methods communicates with the firebase cloud firestore
        * */
    override fun insertPatientFirestore(patientBioData: PatientBioData) {
        firestore.collection(DCH).document(patientBioData.patientId.toString())
            .set(patientBioData).addOnSuccessListener {
                Log.i("Repository", "insertPatientFirestore: ${it}")
            }.addOnFailureListener { e ->
                Log.i("Repository", "insertPatientFirestore: ${e.message}")
            }
    }


    override fun fetchAllRecords(): LiveData<MutableList<PatientBioData>> {
        var listOfRecords: MutableList<PatientBioData> = mutableListOf()

        firestore.collection(DCH)
            .orderBy(
                "patientId",
                Query.Direction.DESCENDING
            )
            .get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    listOfRecords = result.toObjects(PatientBioData::class.java)
                    _allRecords.value = listOfRecords
                }
                Log.i(TAG, "fetchAllRecords: ${result}")

            }.addOnFailureListener { exception ->
                Log.e(TAG, "fetchAllRecords: ", exception)
            }

        return allRecords

    }


    fun insertDailyVitals(patientBioData: DailyVitals) {
        firestore.collection(DAILY_VITALS).document(patientBioData.id.toString())
            .set(patientBioData).addOnSuccessListener {
                Log.i(TAG, "insertDailyVitals: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "insertDailyVitals: ${it.message}")
            }
    }

    fun fetchDailyVitals(): LiveData<MutableList<DailyVitals>> {

        firestore.collection(DAILY_VITALS).get()
            .addOnSuccessListener { result ->
                if (result != null) {

                    _allVitals?.value = result.toObjects(DailyVitals::class.java)
                }
                Log.i(TAG, "fetchAllRecords: ${result}")

            }.addOnFailureListener { exception ->
                Log.e(TAG, "fetchAllRecords: ", exception)
            }

        return allVitals

    }

    //delete patient from vitals queue
    fun removeVitalsQue(position: Int) {
        val removeVitals =
            firestore.collection(DAILY_VITALS)
                .document("$position")
        removeVitals.delete()
    }


    fun insertVitalsRemote(patientID: Int, vitals: Vitals) {

        firestore.collection(DCH).document(patientID.toString())
            .collection(VITALS).document("${vitals.vitalsID}")
            .set(vitals).addOnSuccessListener {
                Log.i(TAG, "insertDailyVitals: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "insertDailyVitals: ${it.message}")
            }
    }


    //
    fun insertDailyConsultation(patientBioData: DailyConsultation) {
        firestore.collection(DAILY_CONSULT).document(patientBioData.consultID.toString())
            .set(patientBioData).addOnSuccessListener {
                Log.i(TAG, "insertDailyVitals: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "insertDailyConsult: ${it.message}")
            }
    }

    fun fetchDailyConsult(): LiveData<MutableList<DailyConsultation>> {
        val allConsults: MutableLiveData<MutableList<DailyConsultation>> = MutableLiveData()
        firestore.collection(DAILY_CONSULT).get()
            .addOnSuccessListener { result ->
                if (result != null) {

                    allConsults.value = result.toObjects(DailyConsultation::class.java)
                }
                Log.i(TAG, "fetchConsults: ${result}")

            }.addOnFailureListener { exception ->
                Log.e(TAG, "fetchAllRecords: ", exception)
            }

        return allConsults

    }
    //delete patient from consultation queue

    override fun removeConsultation(int: Int) {

        firestore.collection(DAILY_CONSULT)
            .document("$int").delete()
    }

    fun fetchAllVitals(position: Int): LiveData<MutableList<Vitals>> {
        var listOVitals: MutableList<Vitals> = mutableListOf()

        val vitals = firestore.collection("$DCH/$position/$VITALS")

        vitals.get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    listOVitals = result.toObjects(Vitals::class.java)
                    _dayVitals.value = listOVitals
                }

                Log.i(TAG, "patient vitals: ${result}")

            }.addOnFailureListener { exception ->
                Log.e(TAG, "vitals no access: ", exception.cause)
            }
        return dayVitals

    }
}
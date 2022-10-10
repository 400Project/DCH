package com.oyatech.dch.database


import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.oyatech.dch.admin.IStaff
import com.oyatech.dch.database.entities.*
import com.oyatech.dch.model.Staff

typealias liveData = (LiveData<MutableList<Any>>)

class Repository(application: Application) :
    IPatient, IConsult, IVitalsId, IDiagnoseId,IStaff {

    private val NEXT_OF_KIN: String="nextOfKins"
    private val STAFF: String = "staff"
    val TAG = Repository::class.java.simpleName
    private var _mao: IDao? = null
    private val mDao get() = _mao!!

    private val _allRecords: MutableLiveData<MutableList<PatientBioData>> = MutableLiveData()
    private val allRecords: LiveData<MutableList<PatientBioData>> = _allRecords!!

    private val _allStaff: MutableLiveData<MutableList<Staff>> = MutableLiveData()
    private val allStaff: LiveData<MutableList<Staff>> = _allStaff!!

    private val _vitalQueue: MutableLiveData<MutableList<DailyVitals>>? = MutableLiveData()
    private val vitalQueue: LiveData<MutableList<DailyVitals>> = _vitalQueue!!

    private var _allDiagnosis: MutableLiveData<MutableList<Diagnose>> = MutableLiveData()
    private val allDiagnosis: LiveData<MutableList<Diagnose>> = _allDiagnosis

    private var _allVitals: MutableLiveData<MutableList<Vitals>> = MutableLiveData()
    private val allVitals: LiveData<MutableList<Vitals>> = _allVitals

    private val firestore = Firebase.firestore
    private val DCH = "dch"
    private val VITALS = "vitals"
    private val DAILY_VITALS = "dailyVitals"
    private val DAILY_CONSULT = "dailyConsult"
    private val DIADNOSIS = "diagnosis"

    init {
        val mIDao = DCHDatabase.getDatabaseInstance(application)
        _mao = mIDao.mDao()
    }

    override fun getCurrentVitals(id: Int): Vitals {

        return mDao.getCurrentVitals(id).last()
    }


    /**
     * Daily Vitals list.
     */
    override fun queueForVitals(dailyVitals: DailyVitals) {
        mDao.queueForVitals(dailyVitals)
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
        firestore.collection(DCH).document(diagnose.patientId.toString())
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

    override fun insertNextOfKin(nextOfKin: NextOfKin) {
        firestore.collection(DCH).document(nextOfKin.patientId.toString())
            .collection(NEXT_OF_KIN).document("${nextOfKin.nextOfKinID}")
            .set(nextOfKin).addOnSuccessListener {
                Log.i(TAG, "insertDailyVitals: ${it}")

            }.addOnFailureListener {
                Log.i(TAG, "insertDailyVitals: ${it.message}")
            }
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

                    _vitalQueue?.value = result.toObjects(DailyVitals::class.java)
                }
                Log.i(TAG, "fetchAllRecords: ${result}")

            }.addOnFailureListener { exception ->
                Log.e(TAG, "fetchAllRecords: ", exception)
            }

        return vitalQueue

    }

    //delete patient from vitals queue
    fun removeVitalsQue(position: Int) {
        val removeVitals =
            firestore.collection(DAILY_VITALS)
                .document("$position")
        removeVitals.delete()
    }


    fun insertVitalsRemote(vitals: Vitals) {

        firestore.collection(DCH).document(vitals.patientId.toString())
            .collection(VITALS).document("${vitals.vitalsID}")
            .set(vitals).addOnSuccessListener {
                Log.i(TAG, "insert Vitals: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "insert Vitals: ${it.message}")
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
        var allVital: MutableList<Vitals> = mutableListOf()

        firestore.collection(DCH)
            .document(position.toString())
            .collection(VITALS).orderBy("date").get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    allVital = result.toObjects(Vitals::class.java)
                    _allVitals.value = allVital

                }

                Log.i(TAG, "patient vitals: ${result}")

            }.addOnFailureListener { exception ->
                Log.e(TAG, "vitals no access: ", exception.cause)
            }
        return allVitals


    }


    override fun insertVitalId(id: ViDs) {
        firestore.collection("vitalsID").document("currentId")
            .set(id).addOnSuccessListener {
                Log.i(TAG, "insertVitalsId: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "insertVitalsId: ${it.message}")
            }
    }

    override fun getVitalsId(): MutableLiveData<Int>{
        val id:MutableLiveData<Int> = MutableLiveData<Int>()

        firestore.collection("vitalsID")
            .document("currentId")
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    id.value = document.toObject(ViDs::class.java)!!.vId

                    Log.i(TAG, "getVitalsId: ${id}")

                }

            }
        return id
    }



    //Storing and retrieving the diagnose ids
    override fun insertDiagnoseId(id: DiagID) {
        firestore.collection("diagnoseID").document("currentId")
            .set(id).addOnSuccessListener {
                Log.i(TAG, "insertDiagnoseId: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "insertDiagnoseId: ${it.message}")
            }
    }

    override fun getDiagnoseId(): MutableLiveData<Int>{
        val id:MutableLiveData<Int> = MutableLiveData<Int>()

        firestore.collection("diagnoseID")
            .document("currentId")
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    id.value = document.toObject(DiagID::class.java)!!.diagnoseId

                    Log.i(TAG, "getVitalsId: ${id}")

                }

            }
        return id
    }

    override fun addStaff(staff: Staff) {
        firestore.collection(STAFF).document(staff.staffId)
            .set(staff).addOnSuccessListener {
                Log.i(TAG, "insertDailyVitals: ${it}")
            }.addOnFailureListener {
                Log.i(TAG, "insertDailyConsult: ${it.message}")
            }
    }

    override fun fetchStaff(): LiveData<MutableList<Staff>> {
        var listOfRecords: MutableList<Staff> = mutableListOf()

        firestore.collection(STAFF)
            .orderBy(
                "dateEmployed",
                Query.Direction.DESCENDING
            )
            .get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    listOfRecords = result.toObjects(Staff::class.java)
                    _allStaff.value = listOfRecords
                }
                Log.i(TAG, "fetchStaff: ${result}")

            }.addOnFailureListener { exception ->
                Log.e(TAG, "fetchStaff: ", exception)
            }

        return allStaff

    }

  fun  getStaff(staffID: String):MutableLiveData<Staff>{
      var aStaff = Staff()
      val staff :MutableLiveData<Staff> = MutableLiveData()
      firestore.collection(STAFF).document(staffID)
         .get().addOnSuccessListener {
              if (it.exists()) {
                  aStaff = it.toObject<Staff>()!!
                  staff.value = aStaff
                  Log.i(TAG, "insertDailyVitals: ${it}")
              }

          }.addOnFailureListener {
              Log.i(TAG, "insertDailyVitals: ${it.message}")
          }
      return  staff
  }

}

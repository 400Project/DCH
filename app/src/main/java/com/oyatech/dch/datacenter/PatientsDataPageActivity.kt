package com.oyatech.dch.datacenter

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.oyatech.dch.Department
import com.oyatech.dch.DepartmentPreference
import com.oyatech.dch.R
import com.oyatech.dch.alerts.snackForError
import com.oyatech.dch.databinding.ActivityPatientsDataPageBinding
import com.oyatech.dch.patient.PatientBioViewModel
import com.oyatech.dch.ui.MainActivity
import com.oyatech.dch.vitals.VitalsViewModel

class PatientsDataPageActivity : MainActivity() {
    private val dpPreference by lazy {
        DepartmentPreference(this)
    }
    val bioViewModel by lazy { ViewModelProvider(this)[PatientBioViewModel::class.java] }
    val vitalsViewModel by lazy { ViewModelProvider(this)[VitalsViewModel::class.java] }
    private var titles = mutableListOf<String>()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPatientsDataPageBinding
    private val DEPARTMENT = "department"
    private var whichDepart: Department? = null

    //   private val viewModel: RegisterNewPatientViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityPatientsDataPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val preference = dpPreference

        val department = preference.department

        if (department != null) {
            whichDepart = whichDepartment(department)
        }

        /**
         * Create a tab layout with PatientBioFragment, Consultation, PharmacyFragment etc
         */

        val tablelayout = binding.tabLayout
        val viewModel = bioViewModel

        val viewPager2 = binding.viewPager
        try {
            viewPager2.adapter = TabAdapter(this, whichDepart!!)
            TabLayoutMediator(tablelayout, viewPager2) { tab, position ->
                run {

                    tab.text = titles[position]
                    setTabBadges(this)
                }
            }.attach()

        } catch (e: Exception) {
            Toast.makeText(this, "No department", Toast.LENGTH_LONG * 3).show()
        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.signOut ->{
                auth.signOut()
                dpPreference.clearDepartment()
                startActivity(Intent(this,MainActivity::class.java))
                Toast.makeText(this,"Signed Out",Toast.LENGTH_LONG * 2).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }

    //Setting the badges for the tabs due to the number of patients in the que
    private fun setTabBadges(lifecycleOwner: LifecycleOwner) {


        lifecycle
        with(binding.tabLayout) {

            bioViewModel.fetchAllRecords().observe(this@PatientsDataPageActivity) { totalRecord ->
                totalRecord.size.also {
                    getTabAt(0)?.orCreateBadge?.number = it
                }
            }
            vitalsViewModel.fetchDailyVitals()
                .observe(this@PatientsDataPageActivity) { waitingForVital ->
                    waitingForVital.size.also {
                        getTabAt(1)?.orCreateBadge?.number = it
                    }
                }
        }

    }

    private fun tabTitle() {
        val subArray = listOf<String>()
    }

    private fun whichDepartment(dp: String): Department? {
        val title =
            arrayListOf("RECORDS", "VITALS", "CONSULTATION", "WARDS", "DISPENSARY", "PHARMACY")
        return if (dp.equals("OPD", true)) {
            titles = title.subList(1, 2)
            Department.OPD
        } else if (dp.equals("Rec", true)) {
            titles = title.subList(0, 1)
            Department.RECORDS
        } else if (dp.equals("IPD", true)) {
            titles = title.subList(1, 4)
            Department.NURSING
        } else if (dp.equals("Adm", true)) {
            titles = title
            Department.ADMINISTRATION
        } else null
    }
}

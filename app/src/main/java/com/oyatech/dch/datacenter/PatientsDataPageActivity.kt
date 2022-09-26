package com.oyatech.dch.datacenter

import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.tabs.TabLayoutMediator
import com.oyatech.dch.Department
import com.oyatech.dch.R
import com.oyatech.dch.patient.PatientBioViewModel
import com.oyatech.dch.databinding.ActivityPatientsDataPageBinding
import com.oyatech.dch.ui.MainActivity
import com.oyatech.dch.vitals.VitalsViewModel

class PatientsDataPageActivity : MainActivity() {
    val bioViewModel by lazy {  ViewModelProvider(this)[PatientBioViewModel::class.java] }
    val vitalsViewModel by lazy { ViewModelProvider(this)[VitalsViewModel::class.java] }
    private val title = arrayListOf( "RECORDS","VITALS","CONSULTATION", "WARDS", "DISPENSARY", "PHARMACY")
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPatientsDataPageBinding

    //   private val viewModel: RegisterNewPatientViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityPatientsDataPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        /**
         * Create a tab layout with PatientBioFragment, Consultation, PharmacyFragment etc
         */

        val tablelayout = binding.tabLayout
val viewModel = bioViewModel

        val viewPager2 = binding.viewPager
        viewPager2.adapter = TabAdapter(this, Department.RECORDS)
        TabLayoutMediator(tablelayout, viewPager2) { tab, position ->
            run {

                tab.text = title[0]
                setTabBadges(this)
            }
        }.attach()
        // val navController = findNavController(R.id.nav_host_fragment_content_patients_data_page)
        /*  appBarConfiguration = AppBarConfiguration(navController.graph)
          setupActionBarWithNavController(navController, appBarConfiguration)

          binding.fab.setOnClickListener { view ->
              Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  .setAnchorView(R.id.fab)
                  .setAction("Action", null).show()
          }*/

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }
//Setting the badges for the tabs due to the number of patients in the que
    private fun setTabBadges(lifecycleOwner: LifecycleOwner) {


    lifecycle
        with(binding.tabLayout) {

            bioViewModel.fetchAllRecords().observe(this@PatientsDataPageActivity){totalRecord ->
                totalRecord.size.also {
                    getTabAt(0)?.orCreateBadge?.number = it
                }
            }
            vitalsViewModel.fetchDailyVitals().observe(this@PatientsDataPageActivity){ waitingForVital ->
                waitingForVital.size.also {
                    getTabAt(1)?.orCreateBadge?.number = it
                }
            }
        }

    }
}

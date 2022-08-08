package com.oyatech.dch.datacenter

import android.os.Bundle
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import com.oyatech.dch.R
import com.oyatech.dch.databinding.ActivityPatientsDataPageBinding
import com.oyatech.dch.patient.RegisterNewPatientViewModel
import com.oyatech.dch.ui.MainActivity

class PatientsDataPage : MainActivity() {
    val viewModel = RegisterNewPatientViewModel.viewModel
    private val title = arrayListOf("RECORD", "VITALS", "CONSULTATION", "PRODUCT")
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
         * Create a tab layout with PatientFragment, Consultation, Product etc
         */

        val tablelayout = binding.tabLayout


        val viewPager2 = binding.viewPager
        viewPager2.adapter = TabAdapter(this)
        TabLayoutMediator(tablelayout, viewPager2) {
                tab, position ->
            run {

                tab.text = title[position]
                setTabBadges()
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

    private fun setTabBadges() {


        with(binding.tabLayout) {
                    getTabAt(0)?.orCreateBadge?.number = viewModel.getBioData().size

                    getTabAt(1)?.orCreateBadge?.number = viewModel.getQueuedForVitals().size

                    getTabAt(2)?.orCreateBadge?.number =
                        viewModel.getQueuedForConsultation().size

        }

    }
}

package com.oyatech.dch.ui

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.oyatech.dch.DepartmentPreference
import com.oyatech.dch.R
import com.oyatech.dch.admin.AdminActivity
import com.oyatech.dch.databinding.HomeActivityMainBinding
import com.oyatech.dch.datacenter.PatientsDataPageActivity

open class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    lateinit var auth: FirebaseAuth
    private var staff_department: String = ""
    private val dpPreference by lazy {
        DepartmentPreference(this)
    }
    private lateinit var binding: HomeActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        drawerLayout = binding.drawer
        navigationView = binding.navigationView

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHost.navController

        //Creating the instance of the appBar by pacing the object of navGraph and the drawer object
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        //Setting up the navController and appBarConfig objects
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        //Setting up the navView with the navController
        navigationView.setupWithNavController(navController)


        navigationView.setNavigationItemSelectedListener {


            with(findNavController(R.id.nav_host_fragment_content_main)) {
                when (it.itemId) {
                    R.id.log_In -> {
                        drawerLayout.closeDrawer(Gravity.LEFT)
                        intent(this)

                    }
                    R.id.servicesFragment -> {
                        this.navigate(R.id.servicesFragment)


                    }R.id.bookingsFragment -> {
                        this.navigate(R.id.bookingsFragment)


                    }
                }

            }
            drawerLayout.closeDrawer(Gravity.LEFT)
            true

        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*  return when (item.itemId) {
              R.id.settings ->{
                  navController.navigate(R.id.ConsultationsFragment)
                  Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show()
                  true
              }
              else -> super.onOptionsItemSelected(item)
          }*/
        /* val navController = findNavController(R.id.nav_host_fragment_content_main)
         return item.onNavDestinationSelected(navController)|| super.onOptionsItemSelected(item)*/
        return false
    }


    //using the onBackPress to close the drawer if it's open
    override fun onBackPressed() {
        with(binding.drawer) {
            if (isOpen) {
                close()
            } else
                super.onBackPressed()
        }

    }

    private fun intent(navController: NavController) {
        val preference = dpPreference
        staff_department = preference.department
        val currentUser = auth.currentUser
        if ((staff_department.isNotEmpty()) && (currentUser != null)) {
            intentToDataCenter(staff_department)
            Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show()
        } else {
            navController.navigate(R.id.LoginFragment)
            Toast.makeText(this, "Signed In Please", Toast.LENGTH_SHORT).show()
        }

    }

    private fun intentToDataCenter(staff_department: String) {
        if (staff_department == "Adm") {
            startActivity(Intent(this, AdminActivity::class.java))
        } else {
            val intent =
                Intent(this, PatientsDataPageActivity::class.java)

            startActivity(intent)
        }
    }

}
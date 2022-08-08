package com.oyatech.dch.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.oyatech.dch.R
import com.oyatech.dch.databinding.HomeActivityMainBinding

open class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    private lateinit var binding: HomeActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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


        /*  navigationView.setNavigationItemSelectedListener {


                 with(findNavController(R.id.nav_host_fragment_content_main)){
                      when (it.itemId){
                      R.id.LoginFragment -> {
                      this.navigate(R.id.LoginFragment)
                  }
                      R.id.gallery -> {
                      this.navigate(R.id.product)
                  }
                  }


              }
                val intent = Intent(this, PatientsDataPage::class.java)
                  startActivity(intent)
             Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
                drawerLayout.closeDrawer(Gravity.LEFT)
                  true

          }*/


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*  return when (item.itemId) {
              R.id.settings ->{
                  navController.navigate(R.id.Consultations)
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
            } else {
                super.onBackPressed()
            }
        }

    }
}
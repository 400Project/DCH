package com.oyatech.dch.ui

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.oyatech.dch.records.PatientsDataPage
import com.oyatech.dch.R
import com.oyatech.dch.databinding.HomeActivityMainBinding

open class MainActivity : AppCompatActivity() {
  private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: HomeActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        drawerLayout = binding.drawer
        navigationView = binding.navigationDrawer

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController  = navHost.navController
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)

      /*  findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController)*/

       navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)


      /*  val toggle = ActionBarDrawerToggle(this,drawerLayout,
            R.string.open,
        R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()*/
        navigationView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.service) {
                val intent = Intent(this, PatientsDataPage::class.java)
                startActivity(intent)
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
                drawerLayout.closeDrawer(Gravity.LEFT)
                true
            } else false
        }


    }



   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      /*  return when (item.itemId) {
            R.id.settings ->{
                navController.navigate(R.id.ADayConsultation)
                Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }*/
      /* val navController = findNavController(R.id.nav_host_fragment_content_main)
       return item.onNavDestinationSelected(navController)|| super.onOptionsItemSelected(item)*/
       return false
    }

    override fun onSupportNavigateUp(): Boolean {
        //Setting clickListeners to the drawer
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
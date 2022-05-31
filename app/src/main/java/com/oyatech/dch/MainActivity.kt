package com.oyatech.dch

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.oyatech.dch.databinding.ActivityMainBinding
import com.oyatech.dch.databinding.HomeActivityMainBinding

class MainActivity : AppCompatActivity() {
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

        findViewById<NavigationView>(R.id.navigation_drawer).setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)


      /*  val toggle = ActionBarDrawerToggle(this,drawerLayout,
            R.string.open,
        R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()*/
    }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      /*  return when (item.itemId) {
            R.id.settings ->{
                navController.navigate(R.id.SecondFragment)
                Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }*/
       val navController = findNavController(R.id.nav_host_fragment_content_main)
       return item.onNavDestinationSelected(navController)|| super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        //Setting clickListeners to the drawer
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
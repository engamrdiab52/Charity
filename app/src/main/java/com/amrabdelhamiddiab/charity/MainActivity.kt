package com.amrabdelhamiddiab.charity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.amrabdelhamiddiab.charity.databinding.ActivityMainBinding
import com.amrabdelhamiddiab.charity.frameWork.PreferenceManager
import com.amrabdelhamiddiab.core.data.IPreferenceHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }
    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    //  private lateInit var fab: FloatingActionButton
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var preferenceHelper: IPreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceHelper = PreferenceManager(this.applicationContext)
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView
        toolbar = binding.appBarMain.toolbar
        //fab = binding.appBarMain.floatingActionButton
        bottomNavigationView = binding.appBarMain.contentMain.bottomNavigationView
        setSupportActionBar(toolbar)
        /* fab.setOnClickListener {
             SnackBar.make(it, "Replace with your own action", SnackBar.LENGTH_LONG)
                 .setAction("Action", null).show()

         }*/
        bottomNavigationView.setupWithNavController(navController)
        navigationView.setupWithNavController(navController)
        // if (it.itemId== R.id.deleteUser)FirebaseAuth.getInstance().currentUser.delete()

        //to hide up button when this fragments selected
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.currentBalanceFragment,
                R.id.historyFragment,
                R.id.profitsFragment,
                R.id.homeFragment
            ), drawerLayout
        )
        //to change title automatically
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_app_bar, menu)
        return true
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
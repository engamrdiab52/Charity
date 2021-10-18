package com.amrabdelhamiddiab.charity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.amrabdelhamiddiab.charity.databinding.ActivityMainBinding
import com.amrabdelhamiddiab.charity.frameWork.PeriodicBackgroundNotification
import com.amrabdelhamiddiab.charity.frameWork.PreferenceManager
import com.amrabdelhamiddiab.core.data.IPreferenceHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.concurrent.TimeUnit
import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.amrabdelhamiddiab.charity.frameWork.LocaleHelper
import com.amrabdelhamiddiab.charity.presentation.settings.SettingsFragment.Companion.ONE_PER_DAY


class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }


    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
    }
    private val constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build()
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var preferenceHelper: IPreferenceHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceHelper = PreferenceManager(this.applicationContext)
        LocaleHelper.setLocale(this, preferenceHelper.getSavedLanguageChoice())
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView
        toolbar = binding.appBarMain.toolbar
        //fab = binding.appBarMain.floatingActionButton
        bottomNavigationView = binding.appBarMain.contentMain.bottomNavigationView
        setSupportActionBar(toolbar)
        bottomNavigationView.setupWithNavController(navController)
        navigationView.setupWithNavController(navController)
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
        firePeriodicNotification()
        if (preferenceHelper.getNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun firePeriodicNotification() {
        if (preferenceHelper.getSavedRemindersChoice() == -1L){
            Log.d(TAG, "intervalTime:::  ${preferenceHelper.getSavedRemindersChoice()}")
             preferenceHelper.setSavedRemindersChoice(ONE_PER_DAY)
            Log.d(TAG, "intervalTime:::  ${preferenceHelper.getSavedRemindersChoice()}")
        }
        val periodicTimeWork = PeriodicWorkRequest.Builder(
            PeriodicBackgroundNotification::class.java,
            preferenceHelper.getSavedRemindersChoice(),
            TimeUnit.MINUTES
        ).addTag("com.amrabdelhamiddiab.charity.periodic-pending-notification")
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                "com.amrabdelhamiddiab.charity-periodic-pending-notification",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicTimeWork
            )
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
               navController.navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

  /*  override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelper22.setLocale(this, preferenceHelper.getSavedLanguageChoice())
    }*/
    override fun attachBaseContext(base: Context){
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }
}
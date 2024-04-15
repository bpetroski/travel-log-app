package com.travelbetadisaster.travel_log

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.travelbetadisaster.travel_log.database.TravelRoomDataBase
import com.travelbetadisaster.travel_log.databinding.ActivityMainBinding
import com.travelbetadisaster.travel_log.ui.bucketList.BucketListModelFactory
import com.travelbetadisaster.travel_log.ui.bucketList.BucketListViewModel
import com.travelbetadisaster.travel_log.ui.journalEntry.EditJournalEntryFragment
import com.travelbetadisaster.travel_log.ui.journalEntry.JournalEntryViewModel
import com.travelbetadisaster.travel_log.ui.journalEntry.JournalItemModelFactory
import com.travelbetadisaster.travel_log.ui.journalList.JournalListModelFactory
import com.travelbetadisaster.travel_log.ui.journalList.JournalListViewModel
import com.travelbetadisaster.travel_log.ui.profile.ProfileFragment
import com.travelbetadisaster.travel_log.ui.profile.ProfileModelFactory
import com.travelbetadisaster.travel_log.ui.profile.ProfileViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // location vals
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient // for pulling current lat/long
    private lateinit var location: Location
    private lateinit var latitude: String
    private lateinit var longitude: String


    // not private so that they can be passed to the correct fragment
    val journalEntryViewModel: JournalEntryViewModel by viewModels {
        JournalItemModelFactory((application as JournalApplication).journalRepository)
    }
    val journalListViewModel: JournalListViewModel by viewModels {
        JournalListModelFactory((application as JournalApplication).journalRepository,
            (application as JournalApplication).locationRepository)
    }
    val bucketListViewModel: BucketListViewModel by viewModels {
        BucketListModelFactory((application as JournalApplication).bucketListRepository)
    }
    val profileViewModel: ProfileViewModel by viewModels {
        ProfileModelFactory((application as JournalApplication).profileRepository)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.newJournalEntry.setOnClickListener {
            EditJournalEntryFragment().show(supportFragmentManager, "New Journal Entry Tag")
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
               R.id.nav_home, R.id.nav_profile, R.id.nav_journal_list, R.id.nav_bucket_list, R.id.nav_maps
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // location perms code
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // call from other fragments for location
    fun callLocation(): Location{
        getCurrentLocation()
        return location
    }

    private fun getCurrentLocation(){
        // reference video https://www.youtube.com/watch?v=mwzKYIB9cQs
        if (checkPerms()){
            if (isLocationEnabled()){
                // get lat and long
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){
                    val location:Location? = it.result
                    if(location==null){
                        Toast.makeText(applicationContext, "There was an Error", Toast.LENGTH_SHORT).show()
                    }else {
                        // get success
                        latitude = location.latitude.toString()
                        longitude = location.longitude.toString()
                    }
                }
            }else {
                // Open Settings and request user to turn on location services
                Toast.makeText(applicationContext, "Please Turn on Location Services", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }else {
            // request perms
            requestPermission()
        }
    }

    companion object{
        private const val PERMISSION_REQUEST_ACCESS_LOCATION=100
    }

    private fun checkPerms(): Boolean{
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    private fun isLocationEnabled(): Boolean{
        val locationManager:LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSION_REQUEST_ACCESS_LOCATION){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext, "Location Access Granted", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            }else{
                Toast.makeText(applicationContext, "Location Access Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
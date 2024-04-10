package com.travelbetadisaster.travel_log

import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
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
import androidx.navigation.fragment.findNavController
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
               R.id.nav_home, R.id.nav_journal_list, R.id.nav_bucket_list, R.id.nav_maps
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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
}
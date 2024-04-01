package com.travelbetadisaster.travel_log.database.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class LocationRepository {

    val searchResults = MutableLiveDataList<Location>>()
    val sortedList = MutableLiveData<List<Location>>()
    private var LocationDao: LocationDao?
    var allEntries: LifeData<List<Location>>?

    init{
       // val db: LocationRoomDatabase? = LocationRoomDatabase.getDatabase(application)
        LocationDao = db?.productDao()
        allEntries = LocationDao?.getAllLocations()
    }

    fun insertLocation(newLocation: Locatin) {
        coroutineScope.launch(Dispatchers.IO) { asyncInsert(newLocation) }
    }

    private fun asyncInsert(Location: Location) {
        LocationDao?.insertLocation(location)
    }

    fun deleteLocation(id: Int) {
        coroutineScope.launch(Dispatchers.IO) { asyncDelete(id) }
    }

    private fun asyncDelete(id: Int) {
        LocationDao?.deleteLocation(id)
    }

    fun findLocation(name: String) {
        coroutineScope.launch(Dispatchers.Main) { searchResults.value = asyncFind(name) }
    }

    private suspend fun asyncFind(name: String): List<Location>? =
        coroutineScope.async(Dispatchers.IO) { return@async LocationDao?.findLocation(name) }.await()

    fun sortEntryAsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortAsc() }
    }

    private suspend fun asyncSotAsc(): List<Location>? =
        coroutineScope.async(Dispatchers.IO) { return@async LocationDao?.sortEntryAsc() }.await()

    fun sortEntryDsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortDesc() }
    }

    private suspend fun asyncSortDesc(): List<Location>? =
        coroutineScope.async(Dispatchers.IO) { return@async LocationDao?.sortEntryDsc() }.await()


}
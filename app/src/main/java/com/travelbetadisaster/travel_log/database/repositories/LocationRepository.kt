package com.travelbetadisaster.travel_log.database.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.travelbetadisaster.travel_log.database.dao.LocationDao
import com.travelbetadisaster.travel_log.database.tables.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LocationRepository(private val locationDao: LocationDao) {
//  changed to match code from https://www.youtube.com/watch?v=-LNg-K7SncM
    private var location: Location? = null
    private val searchResults = MutableLiveData<List<Location>?>()
    private var allLocations: LiveData<List<Location>>? = locationDao?.getAllLocations()
    private var sortedList = MutableLiveData<List<Location>?>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertLocation(newLocation: Location) {
        coroutineScope.launch(Dispatchers.IO) { asyncInsertLocation(newLocation)}
    }

    private fun asyncInsertLocation(location: Location) {
        locationDao?.insertLocation(location)
    }

    fun getLocation(id: Int) :Location? {
        coroutineScope.launch(Dispatchers.IO) { location = asyncGetLocation(id) }
        return location
    }

    private fun asyncGetLocation(id: Int): Location? {
        return locationDao?.getLocation(id)
    }

    fun deleteLocation(id: Int) {
        coroutineScope.launch(Dispatchers.IO) { asyncDeleteLocation(id) }
    }

    private fun asyncDeleteLocation(id: Int) {
        locationDao?.deleteLocation(id)
    }

    fun findVisit(name: String) {
        coroutineScope.launch(Dispatchers.Main) { searchResults.value = asyncFindLocation(name) }
    }
    private suspend fun asyncFindLocation(name: String): List<Location>? =
        coroutineScope.async(Dispatchers.IO) { return@async locationDao?.findLocation(name) }.await()

    fun sortLocationAsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortLocationAsc()}
    }
    private suspend fun asyncSortLocationAsc(): List<Location>? =
        coroutineScope.async(Dispatchers.IO) {
        return@async locationDao?.sortLocationAsc() }.await()
    fun sortLocationDesc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortLocationDesc() }
    }
    private suspend fun asyncSortLocationDesc(): List<Location>? =
        coroutineScope.async(Dispatchers.IO) {
        return@async locationDao?.sortLocationDesc() }.await()


}
package com.travelbetadisaster.travel_log.database.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.travelbetadisaster.travel_log.database.dao.LocationDao
import com.travelbetadisaster.travel_log.database.tables.TbdLocation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LocationRepository(private val locationDao: LocationDao) {
//  changed to match code from https://www.youtube.com/watch?v=-LNg-K7SncM
    private var tbdLocation: TbdLocation? = null
    private val searchResults = MutableLiveData<List<TbdLocation>?>()
    private var allLocations: LiveData<List<TbdLocation>>? = locationDao?.getAllLocations()
    private var sortedList = MutableLiveData<List<TbdLocation>?>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertLocation(newTbdLocation: TbdLocation) {
        coroutineScope.launch(Dispatchers.IO) { asyncInsertLocation(newTbdLocation)}
    }

    private fun asyncInsertLocation(tbdLocation: TbdLocation) {
        locationDao?.insertLocation(tbdLocation)
    }

    fun getLocation(id: Int) :TbdLocation? {
        coroutineScope.launch(Dispatchers.IO) { tbdLocation = asyncGetLocation(id) }
        return tbdLocation
    }

    private fun asyncGetLocation(id: Int): TbdLocation? {
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
    private suspend fun asyncFindLocation(name: String): List<TbdLocation>? =
        coroutineScope.async(Dispatchers.IO) { return@async locationDao?.findLocation(name) }.await()

    fun sortLocationAsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortLocationAsc()}
    }
    private suspend fun asyncSortLocationAsc(): List<TbdLocation>? =
        coroutineScope.async(Dispatchers.IO) {
        return@async locationDao?.sortLocationAsc() }.await()
    fun sortLocationDesc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortLocationDesc() }
    }
    private suspend fun asyncSortLocationDesc(): List<TbdLocation>? =
        coroutineScope.async(Dispatchers.IO) {
        return@async locationDao?.sortLocationDesc() }.await()


}
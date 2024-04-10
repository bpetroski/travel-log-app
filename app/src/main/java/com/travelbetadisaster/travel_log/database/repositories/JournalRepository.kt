package com.travelbetadisaster.travel_log.database.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.travelbetadisaster.travel_log.database.TravelRoomDataBase
import com.travelbetadisaster.travel_log.database.dao.VisitDao
import com.travelbetadisaster.travel_log.database.tables.Visit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class JournalRepository(private val visitDao: VisitDao) {
//  changed to match code from https://www.youtube.com/watch?v=-LNg-K7SncM
    private var visit: Visit? = null
    var allVisits: LiveData<List<Visit>>? = visitDao?.getAllVisit()
    val searchResults = MutableLiveData<List<Visit>>()
    val byLocation = MutableLiveData<List<Visit>>()
    val sortedList = MutableLiveData<List<Visit>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getVisit(id: Int)  {
        coroutineScope.launch(Dispatchers.IO) { visit = asyncGetVisit(id) }
    }

    private fun asyncGetVisit(id: Int) : Visit {
        return visitDao?.getVisit(id)!!
    }

    fun insertVisit(newVisit: Visit) {
        coroutineScope.launch(Dispatchers.IO) { asyncInsertVisit(newVisit)}
    }

    private fun asyncInsertVisit(visit: Visit) {
        visitDao?.insertVisit(visit)
    }

    fun updateVisit(visit: Visit) {
        coroutineScope.launch(Dispatchers.IO) { asyncUpdateVisit(visit)}
    }
    private fun asyncUpdateVisit(visit: Visit) {
        visitDao?.updateVisit(visit)
    }
    fun deleteVisit(id: Int) {
        coroutineScope.launch(Dispatchers.IO) { asyncDeleteVisit(id)}
    }
    private fun asyncDeleteVisit(id: Int) {
        visitDao?.deleteVisit(id)
    }

    fun findVisit(name: String) {
        coroutineScope.launch(Dispatchers.Main) { searchResults.value = asyncFindVisit(name)!!}
    }
    private suspend fun asyncFindVisit(name: String): List<Visit>? =
        coroutineScope.async(Dispatchers.IO) { return@async visitDao?.findVisit(name) }.await()

    fun sortVisitAsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortVisitAsc()!! }
    }
    private suspend fun asyncSortVisitAsc(): List<Visit>? = coroutineScope.async(Dispatchers.IO) {
        return@async visitDao?.sortVisitAsc() }.await()
    fun sortVisitDesc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortVisitDesc()!! }
    }
    private suspend fun asyncSortVisitDesc(): List<Visit>? = coroutineScope.async(Dispatchers.IO) {
        return@async visitDao?.sortVisitDesc() }.await()
    fun sortVisitByLocation() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortVisitByLocation()!! }
    }
    private suspend fun asyncSortVisitByLocation(): List<Visit>? = coroutineScope.async(Dispatchers.IO) {
        return@async visitDao?.sortVisitByLocation() }.await()


}
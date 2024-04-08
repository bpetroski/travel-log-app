package com.travelbetadisaster.travel_log.ui.journalList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.tables.Visit
import com.travelbetadisaster.travel_log.database.repositories.LocationRepository

class JournalListViewModel(application: Application) : AndroidViewModel(application) {

    private val journalRepository: JournalRepository = JournalRepository(application)
    private val locationRepository: LocationRepository = LocationRepository(application)
    val allVisits: LiveData<List<Visit>>? = journalRepository.allVisits
    val searchResults: MutableLiveData<List<Visit>> = journalRepository.searchResults
    val sortedList: MutableLiveData<List<Visit>> = journalRepository.sortedList

    //todo this is probably better suited for the Journal entry view model which will be driving the editJournalEntry fragment
    fun newVisit(visit: Visit) {
        journalRepository.insertVisit(visit)
    }

    fun findVisit(name: String) {
        journalRepository.findVisit(name)
    }

    //coroutines are not nessesarry in the viewmodel because they are already handled in the repository
    fun deleteVisit(id: Int) {
        journalRepository.deleteVisit(id)

    }

    fun getSearchResults(): MutableLiveData<List<Visit>> {
        return searchResults
    }

    fun getAllVisits(): LiveData<List<Visit>>? {
        return allVisits
    }

    fun sortVisitAsc() {
        journalRepository.sortVisitAsc()
    }

    fun sortVisitDesc() {
        journalRepository.sortVisitDesc()
    }

    fun getSortedList(): MutableLiveData<List<Visit>> {
        return sortedList
    }

    fun showVisit(id: Int) {
        //todo navigate to Journal entry fragment, passing the id along
    }

}
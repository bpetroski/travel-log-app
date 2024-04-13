package com.travelbetadisaster.travel_log.ui.journalList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.repositories.LocationRepository
import com.travelbetadisaster.travel_log.database.tables.Visit

class JournalListViewModel(private val journalRepository: JournalRepository, private val locationRepository: LocationRepository) : ViewModel(){

    /*private val journalRepository: JournalRepository = JournalRepository(application)
    private val locationRepository: LocationRepository = LocationRepository(application)*/
    private val allVisits: LiveData<List<Visit>>? = journalRepository.allVisits
    private val searchResults: MutableLiveData<List<Visit>> = journalRepository.searchResults
    private val sortedList: MutableLiveData<List<Visit>> = journalRepository.sortedList
    private val _navigateToJournalEntry = MutableLiveData<Event<NavDirections>>()
    val navigateToJournalEntry: LiveData<Event<NavDirections>> = _navigateToJournalEntry
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
    fun getVisit(id: Int): Visit? {
        val Visit = repository.getVisit()?: createDefaultVisit()
        return visit
    }

    fun showVisit(id: Int) {
        val action = JournalListFragmentDirections.actionJournalListToJournalEntry(id)
        _navigateToJournalEntry.value = Event(action)
        //todo navigate to Journal entry fragment, passing the id along
    }

}
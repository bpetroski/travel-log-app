package com.travelbetadisaster.travel_log.ui.journalEntry

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.repositories.LocationRepository
import com.travelbetadisaster.travel_log.database.tables.TbdLocation
import com.travelbetadisaster.travel_log.database.tables.Visit
import kotlinx.coroutines.launch

class JournalEntryViewModel(private val journalRepository: JournalRepository, private val locationRepository: LocationRepository): ViewModel() {

//    replaced based on code from https://www.youtube.com/watch?v=-LNg-K7SncM
//    private val repository: JournalRepository = JournalRepository(application)

    // Function to save or update a visit entry

    fun getVisit(id: Int) : LiveData<Visit> {
        return journalRepository.getVisit(id)
    }

    fun getLocation(id: Int) :LiveData<TbdLocation> {
        return locationRepository.getLocation(id)
    }
    fun saveVisit(visit: Visit, tbdLocation: TbdLocation) {
        viewModelScope.launch {
            if (visit.id == 0) {
                journalRepository.insertVisit(visit)
                locationRepository.insertLocation(tbdLocation)
            } else {
                journalRepository.updateVisit(visit)
            }
        }
    }
// Function to delete an entry 
    fun deleteVisit(id: Int) {
            journalRepository.deleteVisit(id)
    }
}
package com.travelbetadisaster.travel_log.ui.journalEntry

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.travelbetadisaster.travel_log.database.entities.Visit
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import kotlinx.coroutines.launch

class JournalEntryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JournalRepository = JournalRepository(application)

    // Function to save or update a visit entry
    fun saveVisit(visit: Visit) {
        viewModelScope.launch {
            if (visit.id == 0) {
                repository.insertVisit(visit)
            } else {
                repository.updateVisit(visit)
            }
        }
    }
// Function to delete an entry 
    fun deleteVisit(id: Int) {
        viewModelScope.launch {
            repository.deleteVisit(id)
        }
    }


}

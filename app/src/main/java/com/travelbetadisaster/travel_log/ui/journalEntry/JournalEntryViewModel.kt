package com.travelbetadisaster.travel_log.ui.journalEntry

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.tables.Visit
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class JournalEntryViewModel(private val repository: JournalRepository): ViewModel() {

//    replaced based on code from https://www.youtube.com/watch?v=-LNg-K7SncM
//    private val repository: JournalRepository = JournalRepository(application)

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
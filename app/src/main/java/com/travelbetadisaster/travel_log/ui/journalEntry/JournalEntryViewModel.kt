package com.travelbetadisaster.travel_log.ui.journalEntry

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.tables.Visit
import kotlinx.coroutines.launch


class JournalEntryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JournalRepository = JournalRepository(application)

    fun getVisit(): Visit {
        val visit = repository.getVisit() ?: createDefaultVisit()
        return visit
    }

    private fun createDefaultVisit(): Visit {
        return Visit(
            id = 0,
            location = "Default Location",
            date = System.currentTimeMillis()
        )
    }

    // Function to save or update a visit entry

    fun getVisit(id: Int): Visit? {
        return repository.getVisitById(0)


        TODO("get and return a single visit")
    }
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
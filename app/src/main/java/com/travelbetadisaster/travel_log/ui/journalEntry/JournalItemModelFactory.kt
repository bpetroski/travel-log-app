package com.travelbetadisaster.travel_log.ui.journalEntry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import java.lang.IllegalArgumentException

//    replaced based on code from https://www.youtube.com/watch?v=-LNg-K7SncM
class JournalItemModelFactory(private val repository: JournalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JournalEntryViewModel::class.java))
            return JournalEntryViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
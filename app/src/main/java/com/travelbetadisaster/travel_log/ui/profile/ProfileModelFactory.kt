package com.travelbetadisaster.travel_log.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.travelbetadisaster.travel_log.database.repositories.ProfileRepository
import com.travelbetadisaster.travel_log.ui.journalEntry.JournalEntryViewModel
import java.lang.IllegalArgumentException

class ProfileModelFactory(private val repository: ProfileRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

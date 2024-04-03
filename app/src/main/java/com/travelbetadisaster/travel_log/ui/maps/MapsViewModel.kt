package com.travelbetadisaster.travel_log.ui.maps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.tables.Visit

class MapsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JournalRepository = JournalRepository(application)
    private val allEntries: LiveData<List<Visit>>? = repository.allVisits

    private val _text = MutableLiveData<String>().apply {
        value = "This is map Fragment."
    }

    val text: LiveData<String> = _text
}
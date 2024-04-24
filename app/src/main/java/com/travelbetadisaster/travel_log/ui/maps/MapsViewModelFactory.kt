package com.travelbetadisaster.travel_log.ui.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.repositories.LocationRepository
import com.travelbetadisaster.travel_log.database.repositories.MapRepository
import java.lang.IllegalArgumentException

class MapsViewModelFactory(private val journalRepository: JournalRepository,
                           private val locationRepository: LocationRepository,
    private val mapRepository: MapRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapsViewModel::class.java))
            return MapsViewModel(journalRepository, locationRepository, mapRepository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")

    }
}
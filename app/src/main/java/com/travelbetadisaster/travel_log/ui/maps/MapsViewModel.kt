package com.travelbetadisaster.travel_log.ui.maps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.travelbetadisaster.travel_log.JournalApplication
import com.travelbetadisaster.travel_log.database.dao.SharedPreferencesMapDao
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.repositories.MapRepository
import com.travelbetadisaster.travel_log.database.tables.Visit

class MapsViewModel(application: Application) : AndroidViewModel(application) {

    private val journalRepository: JournalRepository = (application as JournalApplication).journalRepository
    val allEntries: LiveData<List<Visit>>? = journalRepository.allVisits

    private val mapRepository: MapRepository

    init {
        val sharedPreferences = application.getSharedPreferences("app_preferences", Application.MODE_PRIVATE)
        val mapDao = SharedPreferencesMapDao(sharedPreferences)
        mapRepository = MapRepository(mapDao)
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is map Fragment."
    }

    val text: LiveData<String> = _text

    fun getLastKnownLocation(): LatLng? = mapRepository.getLastKnownUserLocation()

    fun saveLocation(location: LatLng) = mapRepository.saveUserLocation(location)
}

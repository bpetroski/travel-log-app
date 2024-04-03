package com.travelbetadisaster.travel_log.database.dao

import android.content.SharedPreferences
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

class SharedPreferencesMapDao(private val sharedPreferences: SharedPreferences) {
    companion object {
        const val LOCATION_KEY = "last_known_location"
    }

    fun getLastKnownLocation(): LatLng? {
        val locationJson = sharedPreferences.getString(LOCATION_KEY, null) ?: return null
        return Gson().fromJson(locationJson, LatLng::class.java)
    }

    fun saveLastKnownLocation(location: LatLng) {
        sharedPreferences.edit().putString(LOCATION_KEY, Gson().toJson(location)).apply()
    }
}

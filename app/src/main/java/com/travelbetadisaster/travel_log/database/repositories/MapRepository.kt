package com.travelbetadisaster.travel_log.database.repositories

import com.travelbetadisaster.travel_log.database.dao.SharedPreferencesMapDao
import com.google.android.gms.maps.model.LatLng


class MapRepository(private val mapDao: SharedPreferencesMapDao) {

    fun getLastKnownUserLocation(): LatLng? = mapDao.getLastKnownLocation()

    fun saveUserLocation(location: LatLng) = mapDao.saveLastKnownLocation(location)

}

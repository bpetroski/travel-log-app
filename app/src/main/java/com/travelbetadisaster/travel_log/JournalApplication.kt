package com.travelbetadisaster.travel_log

import android.app.Application
import com.travelbetadisaster.travel_log.database.TravelRoomDataBase
import com.travelbetadisaster.travel_log.database.repositories.BucketListRepository
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.repositories.LocationRepository
import com.travelbetadisaster.travel_log.database.repositories.MapRepository
import com.travelbetadisaster.travel_log.database.repositories.ProfileRepository

class JournalApplication: Application() {
    private val database by lazy { TravelRoomDataBase.getDatabase(this)}
    val journalRepository by lazy { JournalRepository(database!!.visitDao())}
    val bucketListRepository by lazy {BucketListRepository(database!!.bucketListDao())}
    val locationRepository by lazy {LocationRepository(database!!.locationDao())}
//  val mapRepository by lazy {MapRepository(database!!.mapDao())} no map dao?
    val profileRepository by lazy {ProfileRepository(database!!.userDao(), database!!.userHistoryDao())} // not sure if this will work tbh
}
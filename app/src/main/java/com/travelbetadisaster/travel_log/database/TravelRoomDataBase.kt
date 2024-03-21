package com.travelbetadisaster.travel_log.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.api.Context
import com.travelbetadisaster.travel_log.database.dao.*
import com.travelbetadisaster.travel_log.database.tables.*

@Database(entities = [(BucketList::class), (Location::class),
    (User::class), (UserHistory::class), (Visit::class)], version = 1)
abstract class TravelRoomDataBase: RoomDatabase() {

    //create abstract methods of the DAO interfaces
    abstract fun bucketListDao(): BucketListDao
    abstract fun locationDao(): LocationDao
    abstract fun visitDao(): VisitDao
    abstract fun userDao(): UserDao
    abstract fun userHistoryDao(): UserHistoryDao
    abstract fun VisitDao(): VisitDao

    companion object {
        //instance of the database
        private var INSTANCE: TravelRoomDataBase? = null

        internal fun getDatabase(context: Context) : TravelRoomDataBase? {
            if (INSTANCE == null) {
                synchronized(TravelRoomDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<TravelRoomDataBase>(
                            context.applicationContext, TravelRoomDataBase::class.java, //todo unsure why type should be contxt instead of application when application is what's passed in
                            "travel_database").build()
                    }
                }
            }
            return INSTANCE
        }

    }



}
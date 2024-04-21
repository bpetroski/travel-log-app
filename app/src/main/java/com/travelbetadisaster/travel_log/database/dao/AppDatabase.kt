package com.travelbetadisaster.travel_log.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.travelbetadisaster.travel_log.database.tables.*

// List all entities your database will hold and set the version number.
@Database(entities = [User::class, UserHistory::class, BucketListEntry::class, Location::class, Visit::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userHistoryDao(): UserHistoryDao
    abstract fun bucketListDao(): BucketListDao
    abstract fun locationDao(): LocationDao
    abstract fun visitDao(): VisitDao

    companion object {
        // Volatile ensures that changes made in one thread are visible to all other threads immediately
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Singleton pattern to provide a single instance of the database
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "travel_log.db"
                ).fallbackToDestructiveMigration() // Consider handling migrations more gracefully in production.
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

package com.travelbetadisaster.travel_log.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.travelbetadisaster.travel_log.database.tables.Location

interface LocationDao {
    @Query("SELECT * FROM location WHERE locationID = :id")
    fun getLocation(id: Int): Location
    @Insert
    fun insertLocation(location: Location)
    @Update
    fun updateLocation(location: Location)
    @Query("DELETE FROM location WHERE locationID = :id")
    fun deleteLocation(id: Int)
    @Query("Select * FROM location WHERE locationName LIKE '%' || :name || '%'")
    fun findLocation(name: String): List<Location>
    @Query("SELECT * FROM location")
    fun getAllLocations(): LiveData<List<Location>>
    @Query("SELECT * FROM location ORDER BY locationName ASC")
    fun sortLocationAsc(): List<Location>
    @Query("SELECT * FROM location ORDER BY locationName DESC")
    fun sortLocationDesc(): List<Location>


}
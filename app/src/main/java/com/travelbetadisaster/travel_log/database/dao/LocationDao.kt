package com.travelbetadisaster.travel_log.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.travelbetadisaster.travel_log.database.tables.TbdLocation
@Dao
interface LocationDao {
    @Query("SELECT * FROM location WHERE locationID = :id LIMIT 1")
    fun getLocation(id: Int): LiveData<TbdLocation>
    @Insert
    fun insertLocation(tbdLocation: TbdLocation)
    @Update
    fun updateLocation(tbdLocation: TbdLocation)
    @Query("DELETE FROM location WHERE locationID = :id")
    fun deleteLocation(id: Int)
    @Query("Select * FROM location WHERE name LIKE '%' || :name || '%'")
    fun findLocation(name: String): List<TbdLocation>
    @Query("SELECT * FROM location")
    fun getAllLocations(): LiveData<List<TbdLocation>>
    @Query("SELECT * FROM location ORDER BY name ASC")
    fun sortLocationAsc(): List<TbdLocation>
    @Query("SELECT * FROM location ORDER BY name DESC")
    fun sortLocationDesc(): List<TbdLocation>


}
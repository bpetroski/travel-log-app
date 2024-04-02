package com.travelbetadisaster.travel_log.database.dao

import com.travelbetadisaster.travel_log.database.tables.Visit
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VisitDao {
    @Insert
    fun insertVisit(contact: Visit)

    @Query("SELECT * FROM visits")
    fun getAllVisit(): LiveData<List<Visit>>

    @Query("SELECT * FROM visits WHERE name LIKE '%' || :name || '%'")
    fun findVisit(name: String): List<Visit>

    @Query("DELETE FROM visits WHERE VisitID = :id")
    fun deleteVisit(id: Int)

    @Query("SELECT * FROM visits ORDER BY name ASC")
    fun sortVisitAsc(): List<Visit>

    @Query("SELECT * FROM visits ORDER BY name DESC")
    fun sortVisitDesc(): List<Visit>

    @Query("SELECT * FROM visits ORDER BY location")
    fun sortVisitByLoaction(): List<Visit>
}

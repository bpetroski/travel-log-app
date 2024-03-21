package com.travelbetadisaster.travel_log.database.dao

interface UserHistoryDao {

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.travelbetadisaster.travel_log.database.tables.UserHistory

@Dao
interface UserHistoryDao {

    // TODO("Add query annotations to functions")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(userHistory: UserHistory)
//  @Query("SELECT * FROM ProfileHistory ORDER BY id ASC") TODO("Update SQL queries when db tables are properly made")
    fun getAllHistory(): LiveData<List<UserHistory>>
    fun getHistory(id: Int): UserHistory
    fun deleteHistory(id: Int)
    fun findHistory(title: String): List<UserHistory>
    fun sortHistoryAsc(): List<UserHistory>
    fun sortHistoryDesc():List<UserHistory>

}
package com.travelbetadisaster.travel_log.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.travelbetadisaster.travel_log.database.tables.User

@Dao
interface UserDao {
    
    @Insert(onConflict = OnConflictStrategy.ABORT) // set as abort for now, may change depending on needs
    fun insertUser(newUser: User)
    @Update
    fun updateUser(user: User)
    @Query("SELECT * from users WHERE UserID = :id")
    fun getUser(id: Int): User
}
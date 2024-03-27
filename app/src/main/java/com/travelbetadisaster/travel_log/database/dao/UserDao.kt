package com.travelbetadisaster.travel_log.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.travelbetadisaster.travel_log.database.tables.User

@Dao
interface UserDao {
    
    @Insert(onConflict = OnConflictStrategy.ABORT) // set as abort for now, may change depending on needs
    fun insertUser(newUser: User)
    fun updateUser(user: User)
    fun getUser(id: Int): User
}
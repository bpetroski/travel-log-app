package com.travelbetadisaster.travel_log.database.dao

interface BucketListDao {
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.travelbetadisaster.travel_log.database.tables.BucketList

@Dao
interface BucketListDao {
    @Insert
    fun insertEntry(bucketListEntry: BucketList)

    @Update
    fun updateEntry(bucketListEntry: BucketList)

    @Delete
    fun deleteEntry(bucketListEntry: BucketList)

    @Query("SELECT * FROM bucketlist_entries")
    fun getAllEntries(): List<BucketList>

    @Query("SELECT * FROM bucketlist_entries WHERE id = :id")
    fun getEntry(id: Int): BucketList

    @Query("SELECT * FROM bucketlist_entries WHERE entry LIKE :term")
    fun findEntry(term: String): List<BucketList>

    @Query("SELECT * FROM bucketlist_entries ORDER BY entry ASC")
    fun sortEntryAsc(): List<BucketList>

    @Query("SELECT * FROM bucketlist_entries ORDER BY entry DESC")
    fun sortEntryDesc(): List<BucketList>
}

}
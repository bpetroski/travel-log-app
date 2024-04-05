package com.travelbetadisaster.travel_log.database.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.travelbetadisaster.travel_log.database.tables.BucketListEntry

@Dao
interface BucketListDao {
    @Insert
    fun insertEntry(bucketListEntryEntry: BucketListEntry)

    @Update
    fun updateEntry(bucketListEntryEntry: BucketListEntry)

    @Delete
    fun deleteEntry(bucketListEntryEntry: BucketListEntry)

    @Query("SELECT * FROM bucketlist_entries")
    fun getAllEntries(): List<BucketListEntry>

    @Query("SELECT * FROM bucketlist_entries WHERE id = :id")
    fun getEntry(id: Int): BucketListEntry

    @Query("SELECT * FROM bucketlist_entries WHERE title LIKE :term")
    fun findEntry(term: String): List<BucketListEntry>

    @Query("SELECT * FROM bucketlist_entries ORDER BY title ASC")
    fun sortEntryAsc(): List<BucketListEntry>

    @Query("SELECT * FROM bucketlist_entries ORDER BY title DESC")
    fun sortEntryDesc(): List<BucketListEntry>

}
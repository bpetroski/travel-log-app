package layout
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface Bucket_listDAO {
    @Insert
    fun insertEntry(bucketListEntry: BucketListEntry)

    @Update
    fun updateEntry(bucketListEntry: BucketListEntry)

    @Delete
    fun deleteEntry(bucketListEntry: BucketListEntry)

    @Query("SELECT * FROM bucketlist_entries")
    fun getAllEntries(): List<BucketListEntry>

    @Query("SELECT * FROM bucketlist_entries WHERE id = :id")
    fun getEntry(id: Int): BucketListEntry

    @Query("SELECT * FROM bucketlist_entries WHERE entry LIKE :term")
    fun findEntry(term: String): List<BucketListEntry>

    @Query("SELECT * FROM bucketlist_entries ORDER BY entry ASC")
    fun sortEntryAsc(): List<BucketListEntry>

    @Query("SELECT * FROM bucketlist_entries ORDER BY entry DESC")
    fun sortEntryDesc(): List<BucketListEntry>
}


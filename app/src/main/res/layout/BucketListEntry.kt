package layout
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "bucketlist_entries")
data class BucketListEntry(
    val id: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)
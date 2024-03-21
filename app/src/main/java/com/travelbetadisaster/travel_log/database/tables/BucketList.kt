package com.travelbetadisaster.travel_log.database.tables
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "bucketlist_entries")
class BucketList {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
        private set

    var title: String = ""
        set(value) {
            field = value
        }
        get() = field

    var description: String = ""
        set(value) {
            field = value
        }
        get() = field

    var isCompleted: Boolean = false
        set(value) {
            field = value
        }
        get() = field
}
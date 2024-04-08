package com.travelbetadisaster.travel_log.database.tables
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "bucketlist_entries")
class BucketListEntry {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "completed")
    var isCompleted: Boolean = false


    constructor(id: Int, title: String?, description: String?, isCompleted: Boolean) {
        this.id = id
        this.title = title
        this.description = description
        this.isCompleted = isCompleted
    }

    constructor(title: String?, description: String?, isCompleted: Boolean) {
        this.title = title
        this.description = description
        this.isCompleted = isCompleted
    }

    constructor()
}
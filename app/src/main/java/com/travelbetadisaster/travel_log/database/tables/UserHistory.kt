package com.travelbetadisaster.travel_log.database.tables
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_history")
class UserHistory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "historyId")
    val historyId: Int = 0

    @ColumnInfo(name = "userId")
    val userId: Int = 0

    @ColumnInfo(name = "activityType")
    val activityType: String? = null

    @ColumnInfo(name = "activityDate")
    val activityDate: Date? = null

    @ColumnInfo(name = "description")
    val description: String? = null

    @ColumnInfo(name = "totalMiles")
    val totalMiles: Double = 0.0

    @ColumnInfo(name = "visitId")
    val visitId: Int = 0

}
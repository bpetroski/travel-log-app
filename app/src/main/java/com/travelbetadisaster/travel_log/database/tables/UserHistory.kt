package com.travelbetadisaster.travel_log.database.tables
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_history")
class UserHistory {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "historyId") val historyId: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "activityType") val activityType: String,
    @ColumnInfo(name = "activityDate") val activityDate: Date,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "totalMiles") val totalMiles: Double,
    @ColumnInfo(name = "visitId") val visitId: Int)
}
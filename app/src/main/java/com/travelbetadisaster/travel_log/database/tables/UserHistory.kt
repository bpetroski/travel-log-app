package com.travelbetadisaster.travel_log.database.tables
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_history")
class UserHistory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "historyId")
    var historyId: Int = 0

    @ColumnInfo(name = "userId")
    var userId: Int = 0

    @ColumnInfo(name = "activityType")
    var activityType: String? = null

    @ColumnInfo(name = "activityDate")
    var activityDate: Date? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "totalMiles")
    var totalMiles: Double = 0.0

    @ColumnInfo(name = "visitId")

    var visitId: Int = 0

    constructor() {}

    constructor(id: Int, userId: Int, activityType: String, activityDate: Date, description: String, totalMiles: Double, visitId: Int) {
        this.historyId = id
        this.userId= userId
        this.activityType = activityType
        this.activityDate = activityDate
        this.description = description
        this.totalMiles = totalMiles
        this.visitId = visitId
    }

    constructor(userId: Int, activityType: String, activityDate: Date, description: String, totalMiles: Double, visitId: Int) {
        this.userId= userId
        this.activityType = activityType
        this.activityDate = activityDate
        this.description = description
        this.totalMiles = totalMiles
        this.visitId = visitId
    }

    val visitId: Int = 0


}
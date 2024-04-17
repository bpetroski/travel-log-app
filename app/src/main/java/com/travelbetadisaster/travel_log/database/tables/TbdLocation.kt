package com.travelbetadisaster.travel_log.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
class TbdLocation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "locationID")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "lattitude")
    var lattitude: String? = null

    @ColumnInfo(name = "longitude")
    var longitude: String? = null

    constructor()

    constructor(
        id: Int, name: String, description: String,
        lattitude: String, longitude: String
    ) {

        this.id = id
        this.name = name
        this.description = description
        this.lattitude = lattitude
        this.longitude = longitude

    }

    constructor(
        name: String, description: String,
        lattitude: String, longitude: String
    ) {

        this.name = name
        this.description = description
        this.lattitude = lattitude
        this.longitude = longitude

    }
}

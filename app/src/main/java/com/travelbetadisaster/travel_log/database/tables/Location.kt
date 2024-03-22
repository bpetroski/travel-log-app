package com.travelbetadisaster.travel_log.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
class Location {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "locationID")
    var id:Int = 0

    @ColumnInfo(name = "name")
    var name:String? = null
    var description:String? = null
    var lattitude:String? = null
    var longitude:String? = null

    constructor() {}

    constructor(id: Int, name: String, description: String,
                lattitude: String, longitude: String){

        this.id = id
        this.name = name
        this.description = description
        this.lattitude = lattitude
        this.longitude = longitude

    }

    constructor(name: String, description: String,
                lattitude: String, longitude: String){

        this.name = name
        this.description = description
        this.lattitude = lattitude
        this.longitude = longitude

    }

package com.travelbetadisaster.travel_log.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visits")

class Visit {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "VisitID")
    var id:Int = 0

    @ColumnInfo(name = "name")
    @ColumnInfo(name = "location")
    @ColumnInfo(name = "image")
    @ColumnInfo(name = "text")
    @ColumnInfo(name = "tripID")
    var name:String? = null
    var location:Int = 0
    var image:String? = null
    var text:String? = null
    var tripID:Int = 0

    constructor() {}

    constructor(id: Int, name: String, location: Int,
                image: String, text: String,
                tripID: Int){

        this.id = id
        this.name = name
        this.location = location
        this.image = image
        this.text = text
        this.tripID = tripID

    }

    constructor(name: String, location: Int,
                image: String, text: String,
                tripID: Int){

        this.name = name
        this.location = location
        this.image = image
        this.text = text
        this.tripID = tripID

    }


}
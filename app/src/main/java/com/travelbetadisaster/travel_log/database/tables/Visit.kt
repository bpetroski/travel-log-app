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
    var name:String? = null

    @ColumnInfo(name = "location_id")
    var location:Int = 0

    @ColumnInfo(name = "image")
    var image: Int? = null

    @ColumnInfo(name = "text")
    var text:String? = null

    /*@ColumnInfo(name = "trip_id")
    var tripID:Int = 0
*/
    constructor() {}

    constructor(id: Int, name: String, location: Int,
                image: Int, text: String
                /*tripID: Int*/){

        this.id = id
        this.name = name
        this.location = location
        this.image = image
        this.text = text
        /*this.tripID = tripID*/

    }

    constructor(name: String, location: Int,
                image: Int, text: String
                /*tripID: Int*/){

        this.name = name
        this.location = location
        this.image = image
        this.text = text
        /*this.tripID = tripID*/

    }


}

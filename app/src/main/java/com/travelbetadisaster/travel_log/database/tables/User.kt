package com.travelbetadisaster.travel_log.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var id:Int = 0

    @ColumnInfo(name = "name")
    var name:String? = null

    @ColumnInfo(name = "profilePic")
    var profilePic:String? = null

    @ColumnInfo(name = "bio")
    var bio:String? = null

    @ColumnInfo(name = "homeLocation")
    var homeLocation:String? = null

    @ColumnInfo(name = "email")
    var email:String? = null

    @ColumnInfo(name = "password")
    var password:String? = null

    constructor()

    constructor(id: Int, name: String, profilePic: String,
                bio: String, homeLocation: String,
                email: String, password: String) {

        this.id = id
        this.name = name
        this.profilePic = profilePic
        this.bio = bio
        this.homeLocation = homeLocation
        this.email = email
        this.password = password

    }

    constructor(name: String, profilePic: String,
                bio: String, homeLocation: String,
                email: String, password: String) {

        this.name = name
        this.profilePic = profilePic
        this.bio = bio
        this.homeLocation = homeLocation
        this.email = email
        this.password = password

    }

}

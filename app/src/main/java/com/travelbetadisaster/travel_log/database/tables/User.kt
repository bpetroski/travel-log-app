package com.travelbetadisaster.travel_log.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")

class User {

    @PriamryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var id:Int = 0

    @ColumnInfo(name = "name")
    @ColumnInfo(name = "profilePic")
    @ColumnInfo(name = "bio")
    @ColumnInfo(name = "homeLocation")
    @ColumnInfo(name = "email")
    @ColumnInfo(name = "password")
    var name:String? = null
    var profilePic:String? = null
    var bio:String? = null
    var homeLocation:String? = null
    var email:String? = null
    var password:String? = null

    constructor() {}

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

annotation class ColumnInfo(val name: String)

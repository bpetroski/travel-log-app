package com.travelbetadisaster.travel_log.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.travelbetadisaster.travel_log.database.repositories.ProfileRepository
import com.travelbetadisaster.travel_log.database.tables.User
import com.travelbetadisaster.travel_log.database.tables.UserHistory

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProfileRepository = ProfileRepository(application)
    val user: User? = repository.user

    fun insertUser(newUser: User) {
        repository.insertUser(newUser)
    }
    fun updateUser(user: User) {
        repository.updateUser(user)
    }

    fun insertHistory(userHistory: UserHistory) {
        repository.insertHistory(userHistory)
    }

    fun getAllHistory() {
        repository.getAllHistory()
    }

    fun deleteHistory(id:Int) {
        repository.deleteHistory(id)
    }

    fun findHistory(title: String) {
        repository.findHistory(title)
    }
    fun sortHistoryAsc() {
        repository.sortHistoryAsc()
    }

    fun sortHistoryDesc() {
        repository.sortHistoryDesc()
    }

    fun setNewName(toString: Any) {
        TODO("send new name to user object")
    }

    fun setNewHomeTown(toInt: Any) {
        TODO("Send new homeTown to user object")
    }

    fun setNewDescription(toString: Any) {
        TODO("Send new description to user object")
    }

    fun updateUserProfile() {
        TODO("send updates to database")
    }

}
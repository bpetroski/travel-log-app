package com.travelbetadisaster.travel_log.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.travelbetadisaster.travel_log.database.repositories.ProfileRepository
import com.travelbetadisaster.travel_log.database.tables.User
import com.travelbetadisaster.travel_log.database.tables.UserHistory

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProfileRepository = ProfileRepository(application)
    val user:User= repository.getUser()

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

}
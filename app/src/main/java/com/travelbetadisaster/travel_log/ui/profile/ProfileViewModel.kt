package com.travelbetadisaster.travel_log.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.travelbetadisaster.travel_log.database.repositories.ProfileRepository
import com.travelbetadisaster.travel_log.database.tables.User
import com.travelbetadisaster.travel_log.database.tables.UserHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.MutableLiveData


class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main) // or Dispatchers.IO depending on your needs

    /*private val repository: ProfileRepository = ProfileRepository(application)*/
    var user: User? = repository.user

    fun insertUser(newUser: User) {
        repository.insertUser(newUser)
    }
    
    fun updateUser(user: User) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                repository.updateUser(user)
                // Optionally, you can notify the user of successful update here
                // For example: post a success message to a LiveData object
            } catch (e: Exception) {
                // Log the error
                Log.e("ProfileViewModel", "Failed to update user: ${e.message}")
                // Optionally, you can notify the user of the failure here
                // For example: post an error message to a LiveData object
            }
        }
    }


    fun insertHistory(userHistory: UserHistory) {
        repository.insertHistory(userHistory)
    }

    fun getAllHistory() {
        repository.getAllHistory()
    }

    //TODO renable when and if history is implemented (low priority)
    /*fun deleteHistory(id:Int) {
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
    }*/

    fun setNewName(name: String) {
        user?.let {
            it.name = name
            updateUser(it)  // Assuming updateUser() updates the user in the database
        } ?: run {
            // Handle the case where user is null if necessary
        }
    }

    fun setNewHomeTown(homeTown: Int?) {
        user?.let {
            it.homeLocation = homeTown.toString()
            updateUser(it)  // Assuming updateUser() updates the user in the database
        } ?: run {
            // Handle the case where user is null if necessary
        }
    }


    fun setNewDescription(description: String) {
        user?.let {
            it.bio = description  // Assuming 'bio' is the field name in your User class for the description
            updateUser(it)
        } ?: run {
            // Handle the case where user is null if necessary
        }
    }


    fun updateUserProfile() {
        user?.let {
            updateUser(it)
        } ?: run {
            // Optionally handle the case where there is no user to update
        }
    }


package com.travelbetadisaster.travel_log.database.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.travelbetadisaster.travel_log.database.TravelRoomDataBase
import com.travelbetadisaster.travel_log.database.dao.UserDao
import com.travelbetadisaster.travel_log.database.dao.UserHistoryDao
import com.travelbetadisaster.travel_log.database.tables.User
import com.travelbetadisaster.travel_log.database.tables.UserHistory
import kotlinx.coroutines.*


class ProfileRepository(application: Application) {
    private var userDAO: UserDao?
    private var userHistoryDao: UserHistoryDao?
    /*val searchResults = MutableLiveData<List<User>?>()*/ //there probably isn't a reason to be able to search the users table so this list isn't necessary
    // not sure what I'm meant to do with these
    var user: User? = null
    var allUserHistory: LiveData<List<UserHistory>>?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        // "database" as placeholder TODO("update placeholder for database")
        val db: TravelRoomDataBase? =
            TravelRoomDataBase.getDatabase(application)
        // init vars
        userDAO = db?.userDao()
        userHistoryDao = db?.userHistoryDao()
        allUserHistory = userHistoryDao?.getAllHistory()
    }

    fun insertUser(newUser: User){
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsertUser(newUser)
        }
    }
    private suspend fun asyncInsertUser(user: User){
        userDAO?.insertUser(user)
    }
    fun updateUser(user:User){
        coroutineScope.launch(Dispatchers.IO) {
            asyncUpdateUser(user)
        }
    }
    private suspend fun asyncUpdateUser(user:User){
        userDAO?.updateUser(user)
    }
    fun getUser(id: Int) { //no need for return type as the search results are mapped to the live data list
        coroutineScope.launch(Dispatchers.Main) {
            user = asyncGetUser(id)
        }
    }
    private suspend fun asyncGetUser(id: Int): User? {
        return userDAO?.getUser(id)
    }

    // USER HISTORY
    fun insertHistory(userHistory: UserHistory){
        coroutineScope.launch(Dispatchers.IO){
            asyncInsertHistory(userHistory)
        }
    }
    private suspend fun asyncInsertHistory(userHistory: UserHistory){
        userHistoryDao?.insertHistory(userHistory)
    }
    fun getAllHistory(){
        coroutineScope.launch(Dispatchers.IO){
            asyncGetAllHistory()
        }
    }
    private suspend fun asyncGetAllHistory(): LiveData<List<UserHistory>>?{
        return userHistoryDao?.getAllHistory()
    }
    fun getHistory(id: Int){
        coroutineScope.launch(Dispatchers.IO){
            asyncGetHistory(id)
        }
    }
    private suspend fun asyncGetHistory(id: Int): UserHistory?{
        return userHistoryDao?.getHistory(id)
    }
    fun deleteHistory(id: Int){
        coroutineScope.launch(Dispatchers.IO){
            asyncDeleteHistory(id)
        }
    }
    private suspend fun asyncDeleteHistory(id: Int){
        userHistoryDao?.deleteHistory(id)
    }
    fun findHistory(title: String){
        coroutineScope.launch(Dispatchers.IO){
            asyncFindHistory(title)
        }
    }
    private suspend fun asyncFindHistory(title: String): List<UserHistory>?{
        return userHistoryDao?.findHistory(title)
    }
    fun sortHistoryAsc(){
        coroutineScope.launch(Dispatchers.IO){
            asyncSortHistoryAsc()
        }
    }
    private suspend fun asyncSortHistoryAsc(): List<UserHistory>?{
        return userHistoryDao?.sortHistoryAsc()
    }
    fun sortHistoryDesc(){
        coroutineScope.launch(Dispatchers.IO){
            asyncSortHistoryDesc()
        }
    }
    private suspend fun asyncSortHistoryDesc(): List<UserHistory>?{
        return userHistoryDao?.sortHistoryDesc()
    }

}

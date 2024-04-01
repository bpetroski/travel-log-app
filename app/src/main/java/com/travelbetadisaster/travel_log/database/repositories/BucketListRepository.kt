package com.travelbetadisaster.travel_log.database.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class BucketListRepository {

    val searchResults = MutableLiveDataList<BucketList>>()
    val sortedList = MutableLiveData<List<BucketList>>()
    private var BucketListDao: BucketListDao?
    var allEntries: LifeData<List<BucketList>>?

    init{
      //  val db: BucketListRoomDatabase? = BucketListRoomDatabase.getDatabase(application)
        BucketListDao = db?.productDao()
        allEntries = BucketListDao?.getAllBucketLists()
    }

    fun insertBucketList(newBucketList: BucketList) {
        coroutineScope.launch(Dispatchers.IO) { asyncInsert(newBucketList) }
    }

    private fun asyncInsert(BucketList: BucketList) {
        BucketListDao?.insertBucketList(bucketlist)
    }

    fun deleteBucketList(id: Int) {
        coroutineScope.launch(Dispatchers.IO) { asyncDelete(id) }
    }

    private fun asyncDelete(id: Int) {
        BucketListDao?.deleteBucketList(id)
    }

    fun findBucketList(name: String) {
        coroutineScope.launch(Dispatchers.Main) { searchResults.value = asuyncFind(name) }
    }

    private suspend fun asyncFind(name: String): List<BucketList>? =
        coroutineScope.async(Dispatchers.IO) { return@async BucketListDao?.findBucketList(name) }.await()

    fun sortEntryAsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortAsc() }
    }

    private suspend fun asyncSotAsc(): List<BucketList>? =
        coroutineScope.async(Dispatchers.IO) { return@async BucketListDao?.sortEntryAsc() }.await()

    fun sortEntryDsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortDesc() }
    }

    private suspend fun asyncSortDesc(): List<BucketList>? =
        coroutineScope.async(Dispatchers.IO) { return@async BucketListDao?.sortEntryDsc() }.await()

}
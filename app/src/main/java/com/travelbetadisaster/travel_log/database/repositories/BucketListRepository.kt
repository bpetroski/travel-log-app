package com.travelbetadisaster.travel_log.database.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.travelbetadisaster.travel_log.database.TravelRoomDataBase

import com.travelbetadisaster.travel_log.database.dao.BucketListDao;
import com.travelbetadisaster.travel_log.database.tables.BucketListEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class BucketListRepository(private val bucketListDao: BucketListDao) {
//  changed to match code from https://www.youtube.com/watch?v=-LNg-K7SncM
    private var entry: BucketListEntry? = null
    val searchResults = MutableLiveData<List<BucketListEntry>>()
    var allEntries: LiveData<List<BucketListEntry>>? = bucketListDao?.getAllEntries()
    var sortedList = MutableLiveData<List<BucketListEntry>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertEntry(newEntry: BucketListEntry) {
        coroutineScope.launch(Dispatchers.IO) { asyncInsertEntry(newEntry) }
    }

    private fun asyncInsertEntry(entry: BucketListEntry) {
        bucketListDao?.insertEntry(entry)
    }
    fun getEntry(id:Int) {
        coroutineScope.launch(Dispatchers.IO) { entry = asyncGetEntry(id) }
    }
    private fun asyncGetEntry(id: Int): BucketListEntry? {
        return bucketListDao?.getEntry(id)
    }
    fun updateEntry(entry: BucketListEntry) {
        coroutineScope.launch(Dispatchers.IO) { asyncUpdateEntry(entry) }
    }
    private fun asyncUpdateEntry(entry: BucketListEntry) {
        bucketListDao?.updateEntry(entry)
    }

    fun findEntry(name:String) {
        coroutineScope.launch(Dispatchers.IO) { searchResults.value = asyncFindEntry(name) }
    }
    private suspend fun asyncFindEntry(name: String): List<BucketListEntry> =
        coroutineScope.async(Dispatchers.IO) { return@async bucketListDao?.findEntry(name) }.await()!!

    fun deleteEntry(id: Int) {
        coroutineScope.launch(Dispatchers.IO) { asyncDeleteEntry(id) }
    }

    private fun asyncDeleteEntry(id: Int) {
        bucketListDao?.deleteEntry(id)
    }

    fun sortEntriesAsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortEntriesAsc()!! }
    }

    private suspend fun asyncSortEntriesAsc(): List<BucketListEntry>? =
        coroutineScope.async(Dispatchers.IO) { return@async bucketListDao?.sortEntryAsc() }.await()

    fun sortEntriesDsc() {
        coroutineScope.launch(Dispatchers.Main) { sortedList.value = asyncSortEntriesDesc()!! }
    }

    private suspend fun asyncSortEntriesDesc(): List<BucketListEntry>? =
        coroutineScope.async(Dispatchers.IO) { return@async bucketListDao?.sortEntryDesc()}.await()
    fun setComplete(id: Int) {
        coroutineScope.launch(Dispatchers.IO) { asyncSetComplete(id) }
    }

    private suspend fun asyncSetComplete(id: Int) {
        bucketListDao.setComplete(id)
    }



}

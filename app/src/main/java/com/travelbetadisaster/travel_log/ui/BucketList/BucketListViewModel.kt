package com.travelbetadisaster.travel_log.ui.BucketList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData




class BucketListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BucketListRepository = BucketListRepository(application)
    private val allEntries: LiveData<List<BucketList>>? = repository.allEntries
    private val searchResults: MutableLiveData<List<BucketList>> = repository.searchResults
    private val sortedList: MutableLiveData<List<BucketList>> = repository.sortedList

    fun newEntry(bucketlist: BucketList) {
        repository.newEntry(bucketlist)
    }

    fun findEntry(name: String) {
        repository.findBucketList(name)
    }

    fun deleteEntry(id: Int) {
        repository.deleteBucketList(id)
    }

    fun getSearchResults(): MutableLiveData<List<BucketList>> {
        return searchResults
    }

    fun getallEntries(): LiveData<List<BucketList>>? {
        return allEntries
    }

    fun sortEntryAsc() {
        repository.sortBucketListAsc()
    }

    fun sortEntryDsc() {
        repository.sortBucketListDsc()
    }

    fun getSortedList(): MutableLiveData<List<BucketList>> {
        return SortedList
    }

}
package com.travelbetadisaster.travel_log.ui.bucketList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.travelbetadisaster.travel_log.database.repositories.BucketListRepository
import com.travelbetadisaster.travel_log.database.tables.BucketListEntry


class BucketListViewModel(private val repository: BucketListRepository) : ViewModel() {

    private val allEntries: LiveData<List<BucketListEntry>>? = repository.allEntries
    private val searchResults: MutableLiveData<List<BucketListEntry>> = repository.searchResults
    private val sortedList: MutableLiveData<List<BucketListEntry>> = repository.sortedList

    fun newEntry(entry: BucketListEntry) {
        repository.insertEntry(entry)
    }

    fun findEntry(name: String) {
        repository.findEntry(name)
    }

    fun deleteEntry(id: Int) {
        repository.deleteEntry(id)
    }

    fun getSearchResults(): MutableLiveData<List<BucketListEntry>> {
        return searchResults
    }

    fun getAllEntries(): LiveData<List<BucketListEntry>>? {
        return allEntries
    }

    fun sortEntryAsc() {
        repository.sortEntriesAsc()
    }

    fun sortEntryDsc() {
        repository.sortEntriesDsc()
    }

    fun getSortedList(): MutableLiveData<List<BucketListEntry>> {
        return sortedList
    }

    fun setComplete(id: Int) {
        repository.setComplete(id)
    }

}

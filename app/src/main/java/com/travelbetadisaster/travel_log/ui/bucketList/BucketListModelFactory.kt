package com.travelbetadisaster.travel_log.ui.bucketList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.travelbetadisaster.travel_log.database.repositories.BucketListRepository
import java.lang.IllegalArgumentException

class BucketListModelFactory(private val repository: BucketListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BucketListViewModel::class.java))
            return BucketListViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
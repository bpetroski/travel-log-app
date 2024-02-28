package com.travelbetadisaster.travel_log.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is map Fragment."
    }
    val text: LiveData<String> = _text
}
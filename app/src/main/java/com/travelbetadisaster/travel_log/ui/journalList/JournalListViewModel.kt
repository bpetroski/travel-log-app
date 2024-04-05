package com.travelbetadisaster.travel_log.ui.journalList



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.travelbetadisaster.travel_log.database.repositories.JournalRepository
import com.travelbetadisaster.travel_log.database.entities.Visit
import com.travelbetadisaster.travel_log.database.repositories.LocationRepository
import kotlinx.coroutines.launch

class JournalListViewModel : ViewModel() {

      private val journalRepository: JournalRepository
      private val locationRepository: LocationRepository
   ) : ViewModel() {

      val allVisits: LiveData<List<Visit>> = journalRepository.getAllVisits()
      val searchResults: LiveData<List<Visit>> = MutableLiveData()
      val sortByLocation: LiveData<List<Visit>> = MutableLiveData()


      fun newVisit(visit: Visit) {
         viewModelScope.launch {
            journalRepository.insert(visit)
         }
      }
      fun findVisit(id: Long): LiveData<Visit?> {
         return journalRepository.getVisitById(id)
      }
      fun deleteVisit(visit: Visit) {
         viewModelScope.launch {
            journalRepository.delete(visit)
         }
      }
      //figure out sortVisitAsc() and Dsc()
   }

}
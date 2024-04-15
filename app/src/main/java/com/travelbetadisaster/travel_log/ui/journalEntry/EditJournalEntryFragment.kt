package com.travelbetadisaster.travel_log.ui.journalEntry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.PrimaryKey
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.travelbetadisaster.travel_log.MainActivity
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.repositories.LocationRepository
import com.travelbetadisaster.travel_log.database.tables.Location
import com.travelbetadisaster.travel_log.database.tables.Visit
import com.travelbetadisaster.travel_log.databinding.FragmentEditJournalEntryBinding
import com.travelbetadisaster.travel_log.ui.maps.MapsFragment
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class EditJournalEntryFragment : BottomSheetDialogFragment() {

    //TODO create listeners for loacation button and photo button
    private var _binding: FragmentEditJournalEntryBinding? = null
    private val binding get() = _binding!!

//    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val viewModel: JournalEntryViewModel
        get() = (activity as MainActivity).journalEntryViewModel
    private var entryId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditJournalEntryBinding.inflate(inflater, container, false)

        // Assuming ID as a fragment
        arguments?.let {
            entryId = it.getInt("entryId")
        }

        setupListeners()
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        return binding.root
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            saveEntry()
        }
    }

    private fun saveEntry() {
        val newLocationID = (activity as MainActivity).callLocation().time.toInt()
        val newEntryTitle = binding.journalTitle.text.toString()
//      pulls location title from EditText box and lat/long from device current location using callLocation from MainActivity.
        val newEntryLocation = Location(  // TODO this new location still needs to be inserted into the location table
            id = newLocationID, // this doesn't really work for an id but we're running outta time and I can't figure out how to make it work the right way
            name = binding.journalEntryLocation.text.toString(),
            description = "",
            lattitude = (activity as MainActivity).callLocation().latitude.toString(),
            longitude = (activity as MainActivity).callLocation().longitude.toString()
        )
        val newEntryImage = 0 // TODO connect with image picker. Can probably use constructor because it will be new images.
        val newEntryDescription = binding.journalDescription.text.toString()
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
        val newEntryDateTime = LocalDateTime.now().format(formatter)

        // TODO check for entryID in database to detect if editing (probably needs to be done in the onCreateView, but some code will be needed here to call for an update instead an insert)
        if(newEntryTitle == "" || newEntryDescription == "" || newEntryDateTime == ""){
            Toast.makeText(activity,"There was an error", Toast.LENGTH_SHORT).show()
            return
        }else{
            val entry = Visit(newEntryTitle, newLocationID, 0, newEntryDescription, newEntryDateTime)
            viewModel.saveVisit(entry)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

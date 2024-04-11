package com.travelbetadisaster.travel_log.ui.journalEntry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.travelbetadisaster.travel_log.MainActivity
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.Location
import com.travelbetadisaster.travel_log.database.tables.Visit
import com.travelbetadisaster.travel_log.databinding.FragmentEditJournalEntryBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class EditJournalEntryFragment : BottomSheetDialogFragment() {

    //TODO create listeners for loacation button and photo button
    private var _binding: FragmentEditJournalEntryBinding? = null
    private val binding get() = _binding!!

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

        return binding.root
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            saveEntry()
        }
    }

    private fun saveEntry() {
        val newEntryTitle = binding.journalTitle.text.toString()
        val newEntryLocation = 0 // TODO not sure how to make this work. I don't think we have a function to pull the LocationID
        val newEntryImage = 0 // TODO connect with image picker. Can probably use constructor because it will be new images.
        val newEntryDescription = binding.journalDescription.text.toString()
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
        val newEntryDateTime = LocalDateTime.now().format(formatter)

        // TODO check for entryID in database to detect if editing (probably needs to be done in the onCreateView, but some code will be needed here to call for an update instead an insert)
        if(newEntryTitle == "" || newEntryDescription == "" || newEntryDateTime == ""){
            Toast.makeText(activity,"There was an error", Toast.LENGTH_SHORT).show()
            return
        }else{
            val entry = Visit(newEntryTitle, 0, 0, newEntryDescription, newEntryDateTime)
            viewModel.saveVisit(entry)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

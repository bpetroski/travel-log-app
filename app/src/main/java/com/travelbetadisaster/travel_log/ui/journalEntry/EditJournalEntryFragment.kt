package com.travelbetadisaster.travel_log.ui.journalEntry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.travelbetadisaster.travel_log.MainActivity
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.Location
import com.travelbetadisaster.travel_log.database.tables.Visit
import com.travelbetadisaster.travel_log.databinding.FragmentEditJournalEntryBinding


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
        //todo should also grab the name, locationID, and imageId before constructing a visit to pass to the method
        // todo Location constructor to pass location ID
        val entry = Visit(binding.journalTitle.text.toString(), 0, 0, binding.journalDescription.text.toString())
        viewModel.saveVisit(entry)
    }

    private fun enableEditing() {

    }

    private fun deleteEntry(id: Int) {
        viewModel.deleteVisit(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

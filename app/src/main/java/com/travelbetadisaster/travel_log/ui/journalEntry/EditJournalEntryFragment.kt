package com.travelbetadisaster.travel_log.ui.journalEntry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.travelbetadisaster.travel_log.databinding.FragmentEditJournalEntryBinding

class EditJournalEntryFragment : Fragment() {

    private var _binding: FragmentEditJournalEntryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: JournalEntryViewModel
    private var entryId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditJournalEntryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[JournalEntryViewModel::class.java]

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

    //Todo should also grab the name, locationID, and imageId before constructing a visit to pass to the method
    private fun saveEntry() {
        val entry = binding.journalDescription.text // need to format this as a "Visit" with a Visit constructor
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

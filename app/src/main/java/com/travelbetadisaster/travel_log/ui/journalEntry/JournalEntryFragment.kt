package com.travelbetadisaster.travel_log.ui.journalEntry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.travelbetadisaster.travel_log.databinding.FragmentJournalEntryBinding

class JournalEntryFragment : Fragment() {

    private var _binding: FragmentJournalEntryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: JournalEntryViewModel
    private var entryId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalEntryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[JournalEntryViewModel::class.java]

        // Assuming ID as a fragment
        arguments?.let {
            entryId = it.getInt("entryId")
        }

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
/* //this button should be in the edit fragment
        binding.btnSave.setOnClickListener {
            saveEntry()
        }
*/
        //todo set button names correctly
        binding.btnEdit.setOnClickListener {
            enableEditing()
        }

        binding.btnDelete.setOnClickListener {
            entryId?.let { id ->
                deleteEntry(id)
            }
        }
    }

    //this method exists in the edit journal entry fragment
    /*private fun saveEntry() {
        val entry = binding.journalDescription.text
        viewModel.saveVisit(entry)
    }*/

    private fun enableEditing() {
        //todo this method should navigate to the edit fragment
    }

    private fun deleteEntry(id: Int) {
        viewModel.deleteVisit(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

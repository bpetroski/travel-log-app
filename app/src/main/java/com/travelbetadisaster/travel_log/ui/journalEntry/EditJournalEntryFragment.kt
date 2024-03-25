package com.travelbetadisaster.travel_log.ui.journalEntry

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travelbetadisaster.travel_log.R

class EditJournalEntryFragment : Fragment() {

    companion object {
        fun newInstance() = EditJournalEntryFragment()
    }

    private lateinit var viewModel: JournalEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_journal_entry, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JournalEntryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
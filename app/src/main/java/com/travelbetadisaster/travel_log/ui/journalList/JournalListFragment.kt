package com.travelbetadisaster.travel_log.ui.journalList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travelbetadisaster.travel_log.R

class JournalListFragment : Fragment() {

    companion object {
        fun newInstance() = JournalListFragment()
    }

    private lateinit var viewModel: JournalListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_journal_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JournalListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
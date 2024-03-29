package com.travelbetadisaster.travel_log.ui.journalList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travelbetadisaster.travel_log.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.travelbetadisaster.travel_log.databinding.FragmentJournalListBinding
import com.travelbetadisaster.travel_log.ui.journalList.adapter.JournalListAdapter
import com.travelbetadisaster.travel_log.ui.journalList.viewModel.JournalListViewModel


class JournalListFragment : Fragment() {

    private lateinit var adapter: JournalListAdapter
    private lateinit var viewModel: JournalListViewModel
    private var _binding: FragmentJournalListBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = JournalListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_journal_list, container, false)
    }

    listenerSetup()
    observerSetup()
    recyclerSetup()

    return view
}

private fun listenerSetup() {
    binding.cardView.setOnClickListener { onCardViewClick() }
}

private fun observerSetup() {
    viewModel.journalList.observe(viewLifecycleOwner) { journals ->
        adapter.submitList(journals)
    }
}

private fun recyclerSetup() {
    adapter = JournalListAdapter()
    binding.recyclerView.adapter = adapter
    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    adapter.setOnItemClickListener { journal ->
        onListClick(journal)
    }
}

private fun onListClick(journal: Journal) {
}

private fun onCardViewClick() {
}


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(JournalListViewModel::class.java)
        // TODO: Use the ViewModel
    }

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
    }

}

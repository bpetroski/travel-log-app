package com.travelbetadisaster.travel_log.ui.journalList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.travelbetadisaster.travel_log.databinding.FragmentJournalListBinding



class JournalListFragment : Fragment(), OnItemClickListener  {

    private var adapter: JournalListAdapter? = null
    val viewModel: JournalListViewModel by viewModels()
    private var _binding: FragmentJournalListBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = JournalListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJournalListBinding.inflate(inflater, container, false)
        listenerSetup()
        observerSetup()
        recyclerSetup()

        return binding.root
    }


    private fun listenerSetup() {
        binding.cardView.setOnClickListener { onCardViewClick() }
    }

    private fun observerSetup() {
        viewModel.getAllVisits()?.observe(viewLifecycleOwner) { visits ->
            visits?.let { adapter?.setVisitList(it) }
        }
        viewModel.getSearchResults()?.observe(viewLifecycleOwner) { visits ->
            visits?.let {
                if (it.isNotEmpty()) {
                    adapter?.setVisitList(it)
                } else
                    Toast.makeText(
                        activity, "You must enter a search criteria",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
        viewModel.journalList.observe(viewLifecycleOwner) { journals ->
            adapter.submitList(journals)
        }
    }

    private fun recyclerSetup() {
        adapter = JournalListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onListClick(journal: Journal) {
    }

    override fun onItemClick(int: Int) {
        viewModel.showVisit(int)
    }

}

package com.travelbetadisaster.travel_log.ui.bucketList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.travelbetadisaster.travel_log.MainActivity
import com.travelbetadisaster.travel_log.databinding.FragmentBucketListBinding
import com.travelbetadisaster.travel_log.ui.journalList.BucketListAdapter
import com.travelbetadisaster.travel_log.ui.journalList.OnCompleteClickListener
import com.travelbetadisaster.travel_log.ui.journalList.OnEntryClickListener

class BucketListFragment : Fragment(), OnEntryClickListener, OnCompleteClickListener {

    companion object {
        fun newInstance() = BucketListFragment()
    }

    private var adapter: BucketListAdapter? = null
    private val viewModel: BucketListViewModel get() = (activity as MainActivity).bucketListViewModel
    private var _binding: FragmentBucketListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBucketListBinding.inflate(inflater, container, false)
        listenerSetup()
        observerSetup()
        recyclerSetup()

        return binding.root
    }

    private fun listenerSetup() {
        //todo implement when rest of buttons are added to layout
    }
    private fun observerSetup() {
        viewModel.getAllEntries()?.observe(viewLifecycleOwner) { visits ->
            visits?.let { adapter?.setBucketList(it) }
        }
        viewModel.getSearchResults().observe(viewLifecycleOwner) { visits ->
            visits?.let {
                if (it.isNotEmpty()) {
                    adapter?.setBucketList(it)
                } else
                    Toast.makeText(
                        activity, "You must enter a search criteria",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
        viewModel.getSortedList().observe(viewLifecycleOwner) { visits ->
            visits?.let {
                if (it.isNotEmpty()) {
                    adapter?.setBucketList(it)
                }
            }
        }
    }

    private fun recyclerSetup() {
        adapter = BucketListAdapter(this, this)
        binding.bucketListRecycler.adapter = adapter
        binding.bucketListRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClick(id: Int) {
        viewModel.showEntry(id)
    }

    override fun onCompleteClick(id: Int) {
        viewModel.setComplete(id)
    }

}
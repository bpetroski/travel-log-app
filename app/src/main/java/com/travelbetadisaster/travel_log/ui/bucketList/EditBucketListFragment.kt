package com.travelbetadisaster.travel_log.ui.bucketList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.travelbetadisaster.travel_log.MainActivity
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.BucketListEntry
import com.travelbetadisaster.travel_log.databinding.FragmentEditBucketListBinding



class EditBucketListFragment : Fragment() {

    private var _binding: FragmentEditBucketListBinding? = null
    private val binding get() =_binding!!
    private val viewModel: BucketListViewModel get() = (activity as MainActivity).bucketListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentEditBucketListBinding.inflate(inflater, container, false)
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener{
            saveEntry()
        }
    }
    private fun saveEntry() {
        val newEntry: BucketListEntry = BucketListEntry(binding.NewBucketListTitle.text.toString(),
            binding.journalDescription.text.toString(), false)
        viewModel.newEntry(newEntry)
        findNavController().navigate(R.id.action_editBucketListFragment_to_nav_bucket_list)
    }

}

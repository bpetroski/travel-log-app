package com.travelbetadisaster.travel_log.ui.journalEntry

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.travelbetadisaster.travel_log.MainActivity
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.TbdLocation
import com.travelbetadisaster.travel_log.database.tables.Visit
import com.travelbetadisaster.travel_log.databinding.FragmentJournalEntryBinding
import java.io.File

class JournalEntryFragment : Fragment() {

    private var _binding: FragmentJournalEntryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JournalEntryViewModel get() = (activity as MainActivity).journalEntryViewModel
    private var entryId: Int? = null
    private val args: JournalEntryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalEntryBinding.inflate(inflater, container, false)
        //get the id from safeargs
        entryId =args.VisitId
        viewModel.getVisit(entryId!!).observe(viewLifecycleOwner) {visit->
            visit?.let {
                observeLocation(it.location)
                binding.journalTitle.text = it.name
                binding.journalEntryDescription.text = it.text
                binding.journalEntryDateTime.text = it.date
                if (it.image != 0) {
                    binding.journalEntryImage.setImageBitmap(
                        BitmapFactory.decodeFile(
                            "/data/data/com.travelbetadisaster.travel_log/files/journal_image_${it.image.toString()}.jpg"))
                } else
                    binding.journalEntryImage.setImageResource(R.drawable.placeholder_image)
            }
        }

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        binding.btnEdit.setOnClickListener {
            enableEditing()
        }

        binding.btnDelete.setOnClickListener {
            entryId?.let { id ->
                deleteEntry(id)
            }
        }
    }

    private fun enableEditing() {
        val action = JournalEntryFragmentDirections.actionJournalEntryFragmentToEditJournalEntryFragment(entryId!!)
        findNavController().navigate(action)
    }

    private fun deleteEntry(id: Int) {
        viewModel.deleteVisit(id)
        findNavController().navigate(R.id.action_journalEntryFragment_to_nav_journal_list2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeLocation(id: Int) {
        viewModel.getLocation(id).observe(viewLifecycleOwner) { tbdlocation->
           tbdlocation?.let {
            binding.journalEntryLocation.text = it.name }
        }
    }
}

package com.travelbetadisaster.travel_log.ui.journalEntry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.travelbetadisaster.travel_log.MainActivity
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.Location
import com.travelbetadisaster.travel_log.database.tables.Visit
import com.travelbetadisaster.travel_log.databinding.FragmentJournalEntryBinding

class JournalEntryFragment : Fragment() {

    private var _binding: FragmentJournalEntryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JournalEntryViewModel get() = (activity as MainActivity).journalEntryViewModel
    private var entryId: Int? = null
    private var visit: Visit? = null
    private var location: Location? = null
    private val args: JournalEntryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalEntryBinding.inflate(inflater, container, false)

        //get the id from safeargs
        entryId =args.VisitId
        visit = viewModel.getVisit(id)
        location = viewModel.getLocation(visit?.location!!)

        binding.journalTitle.text = visit?.name
        binding.journalEntryDescription.text = visit?.text
        binding.journalEntryDateTime.text = visit?.date
        /*binding.journalEntryImage.setImageResource(visit?.image!!)*/ //todo uncomment when image is working
        binding.journalEntryLocation.text = location.toString()

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
/* //this button should be in the edit fragment
        binding.btnSave.setOnClickListener {
            saveEntry()
        }
*/
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
        val action = JournalEntryFragmentDirections.actionJournalEntryFragmentToEditJournalEntryFragment(entryId!!)
        findNavController().navigate(action)
    }

    private fun deleteEntry(id: Int) {
        viewModel.deleteVisit(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

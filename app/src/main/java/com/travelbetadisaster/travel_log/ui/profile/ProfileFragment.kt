package com.travelbetadisaster.travel_log.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travelbetadisaster.travel_log.R


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root


        viewModel = ViewModelProvider(this).get(ProfileViewModelViewModel::class.java)


        listenerSetup()

        return view
    }

    private fun listenerSetup() {
        binding.editButton.setOnClickListener { onEditClick() }
        binding.saveButton.setOnClickListener { onSaveClick() }
        binding.xButton.setOnClickListener { onXClick() }
        binding.historyButton.setOnClickListener { onHistoryClick() }
    }
    private fun onEditClick() {
        // Handle edit button click

    }

    private fun onSaveClick() {
        // Handle save button click

    }

    private fun onXClick() {
        // Handle X button click

    }

    private fun onHistoryClick() {
        // Handle history button click

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
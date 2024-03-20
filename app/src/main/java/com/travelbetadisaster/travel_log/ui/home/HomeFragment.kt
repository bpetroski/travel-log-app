package com.travelbetadisaster.travel_log.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.textHome.text = it
        }

        setupListeners()

        return root
    }
    // Set Up Listeners for on click buttons to navigate
    private fun setupListeners() {
        binding.journalEntryButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_journalEntryFragment)
        }

        binding.mapsButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_maps)
        }

        // Add more listeners
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.travelbetadisaster.travel_log.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonToJournalList = view.findViewById<Button>(R.id.button_to_journal_list)
        val buttonToProfile = view.findViewById<Button>(R.id.button)
        val buttonToMaps = view.findViewById<Button>(R.id.navigateToMaps)

        buttonToJournalList.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_journalListFragment)
        }

        buttonToProfile.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_profileFragment)
        }

        buttonToMaps.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_maps)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
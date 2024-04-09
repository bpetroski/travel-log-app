package com.travelbetadisaster.travel_log.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.User
import com.travelbetadisaster.travel_log.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var textViewName: TextView
    private lateinit var textViewHomeTown: TextView
    private lateinit var textViewDescription: TextView

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        textViewName = view.findViewById(R.id.name)
        textViewHomeTown = view.findViewById(R.id.hometown)
        textViewDescription = view.findViewById(R.id.bioContainer)

        listenerSetup()

        return view
    }

    private fun listenerSetup() {
        TODO("set button names correctly")
        TODO("there are no buttons on fragment")
        /*binding.editButton.setOnClickListener { onEditClick() }
        binding.xButton.setOnClickListener { onXClick() }*/
        /*binding.historyButton.setOnClickListener { onHistoryClick() }*/ TODO( "if history is implemented in time re-enable this")
    }

    private fun updateUserProfileUI(user: User) {
        textViewName.text = user.name
        textViewHomeTown.text = user.homeLocation
        textViewDescription.text = user.bio
    }

    private fun onEditClick() {
        TODO(" navigate to the edit profile fragment")

    }


    private fun onXClick() {
        //todo navigate back to home fragment

    }

    private fun onHistoryClick() {
        // Handle history button click

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
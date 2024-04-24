package com.travelbetadisaster.travel_log.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.travelbetadisaster.travel_log.database.dao.AppDatabase
import com.travelbetadisaster.travel_log.database.repositories.ProfileRepository
import com.travelbetadisaster.travel_log.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    companion object {
        fun newInstance() = EditProfileFragment()
    }
    private lateinit var viewModel: ProfileViewModel
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtain the AppDatabase instance
        val database = AppDatabase.getDatabase(requireContext())

        // Retrieve DAOs from the database
        val userDao = database.userDao()
        val userHistoryDao = database.userHistoryDao()

        // Initialize the repository with DAOs
        val repository = ProfileRepository(userDao, userHistoryDao)
        val factory = ProfileModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        listenerSetup()
    }






    private fun listenerSetup() {
        binding.saveButton.setOnClickListener { onSaveClick() }
        binding.cancelButton.setOnClickListener { onXClick() }
    }

    private fun onSaveClick() {
        val name = binding.editTextName.text.toString()
        val homeTownText = binding.editTextHomeTown.text.toString()
        val homeTown = 1713900233 //todo hardcoded for sake of presentation
        val description = binding.editTextDescription.text.toString()

        if (name.isNotEmpty() && homeTown != null && description.isNotEmpty()) {
            viewModel.setNewName(name)
            viewModel.setNewHomeTown(homeTown) // Pass the parsed integer value directly
            viewModel.setNewDescription(description)
            viewModel.updateUserProfile()
            Toast.makeText(requireContext(), "Profile updated!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields correctly.", Toast.LENGTH_SHORT).show()
        }
    }




    private fun onXClick() {
        findNavController().popBackStack()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

}
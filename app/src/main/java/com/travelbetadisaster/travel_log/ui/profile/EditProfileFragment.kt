package com.travelbetadisaster.travel_log.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        listenerSetup()
    }

    private fun listenerSetup() {
        binding.saveButton.setOnClickListener { onSaveClick() }
        binding.cancelButton.setOnClickListener { onXClick() }
    }

    private fun onSaveClick() {
        viewModel.setNewName(binding.editTextName.text.toString())
        viewModel.setNewHomeTown(binding.editTextHomeTown.text.toString().toInt())
        viewModel.setNewDescription(binding.editTextDescription.text.toString())
        viewModel.updateUserProfile()
        Toast.makeText(requireContext(), "Profile updated!", Toast.LENGTH_SHORT).show()
    }

    private fun onXClick() {
        //todo return to profile fragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
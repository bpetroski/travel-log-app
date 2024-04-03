package com.travelbetadisaster.travel_log.ui.profile

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.User


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var textViewName: TextView
    private lateinit var textViewHomeTown: TextView
    private lateinit var textViewDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        textViewName = view.findViewById(R.id.textViewName)
        textViewHomeTown = view.findViewById(R.id.textViewHomeTown)
        textViewDescription = view.findViewById(R.id.textViewDescription)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            user?.let { updateUserProfileUI(it) }
        })
    }

    private fun updateUserProfileUI(user: User) {
        textViewName.text = user.name
        textViewHomeTown.text = user.hometown
        textViewDescription.text = user.description
    }

}
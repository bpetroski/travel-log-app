package com.travelbetadisaster.travel_log.ui.bucketList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travelbetadisaster.travel_log.R

class BucketListFragment : Fragment() {

    companion object {
        fun newInstance() = BucketListFragment()
    }

    private lateinit var viewModel: BucketListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bucket_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BucketListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.dev20.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev20.themarsroll.R
import com.dev20.themarsroll.adapters.MarsPhotoAdapter
import com.dev20.themarsroll.util.Resource
import com.dev20.ui.MarsActivity
import com.dev20.ui.MarsPhotoViewModel
import kotlinx.android.synthetic.main.fragment_curiosity.*


class CuriosityFragment : Fragment(R.layout.fragment_curiosity) {
    lateinit var viewModel: MarsPhotoViewModel
    lateinit var marsPhotoAdapter: MarsPhotoAdapter
    
    val TAG = "CuriosityFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MarsActivity).viewModel
        setupRecyclerView()

        swipeLayout.setOnRefreshListener {
            viewModel.getRandomPhotos()
            swipeLayout.isRefreshing = false
        }

        marsPhotoAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("photo", it)
            }
            findNavController().navigate(
                R.id.action_curiosityFragment_to_cameraFragment,
                bundle
            )
        }

        viewModel.marsPhotos.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { curiosityResponse ->
                    marsPhotoAdapter.differ.submitList(curiosityResponse.photos)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An Error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        curiosityPaginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        curiosityPaginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        marsPhotoAdapter = MarsPhotoAdapter()
        rvCuriosityPhotos.apply {
        adapter = marsPhotoAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
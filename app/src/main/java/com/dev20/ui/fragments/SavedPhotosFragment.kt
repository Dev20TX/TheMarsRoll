package com.dev20.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev20.themarsroll.R
import com.dev20.themarsroll.adapters.MarsPhotoAdapter
import com.dev20.ui.MarsActivity
import com.dev20.ui.MarsPhotoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved_photos.*

class SavedPhotosFragment : Fragment(R.layout.fragment_saved_photos) {
    lateinit var viewModel: MarsPhotoViewModel
    lateinit var marsPhotoAdapter: MarsPhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MarsActivity).viewModel
        setupRecyclerView()

        marsPhotoAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("photo", it)
            }
            findNavController().navigate(
                R.id.action_savedPhotosFragment_to_cameraFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val photo = marsPhotoAdapter.differ.currentList[position]
                viewModel.deletePhoto(photo)
                Snackbar.make(view, "Successfully deleted photo", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.savePhoto(photo)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedPhotos)
        }

        viewModel.getSavedPhotos().observe(viewLifecycleOwner, Observer {photos ->
            marsPhotoAdapter.differ.submitList(photos)

        })
    }

    private fun setupRecyclerView() {
        marsPhotoAdapter = MarsPhotoAdapter()
        rvSavedPhotos.apply {
            adapter = marsPhotoAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
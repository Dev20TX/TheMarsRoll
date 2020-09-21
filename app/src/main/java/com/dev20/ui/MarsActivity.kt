package com.dev20.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dev20.themarsroll.R
import com.dev20.themarsroll.db.PhotosDatabase
import com.dev20.themarsroll.repository.MarsPhotoRepository
import kotlinx.android.synthetic.main.activity_mars.*

class MarsActivity : AppCompatActivity() {

    lateinit var viewModel : MarsPhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mars)

        val marsPhotoRepository = MarsPhotoRepository(PhotosDatabase(this))
        val viewModelProviderFactory = MarsPhotoViewModelProviderFactory(marsPhotoRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MarsPhotoViewModel::class.java)
        bottomNavigationView.setupWithNavController(marsNavHostFragment.findNavController())
    }
}
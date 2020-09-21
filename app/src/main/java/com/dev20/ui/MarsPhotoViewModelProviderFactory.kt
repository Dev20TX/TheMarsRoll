package com.dev20.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev20.themarsroll.repository.MarsPhotoRepository

class MarsPhotoViewModelProviderFactory (
    private val marsPhotoRepository: MarsPhotoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MarsPhotoViewModel(marsPhotoRepository) as T
    }
}
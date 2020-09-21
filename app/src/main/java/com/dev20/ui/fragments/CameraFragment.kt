package com.dev20.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dev20.themarsroll.R
import com.dev20.ui.MarsActivity
import com.dev20.ui.MarsPhotoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_camera.*

class CameraFragment : Fragment(R.layout.fragment_camera) {
    lateinit var viewModel: MarsPhotoViewModel
    val args: CameraFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MarsActivity).viewModel
        val photo = args.photo
        webView.apply {
            setInitialScale(1)
            settings.javaScriptEnabled = false
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true

            setPadding(0, 0, 0, 0)
            layout(0, 0, 0, 0)
            webViewClient = WebViewClient()
            loadUrl(photo.imgSrc)
        }

        fab.setOnClickListener {
            viewModel.savePhoto(photo)
            Snackbar.make(view, "Photo saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}



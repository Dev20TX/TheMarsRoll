package com.dev20.themarsroll.models


data class MarsPhotos(
    val photos: MutableList<Photo>,
    val camera: MutableList<Camera>
)
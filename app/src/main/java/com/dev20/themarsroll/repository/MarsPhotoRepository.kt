package com.dev20.themarsroll.repository

import com.dev20.themarsroll.api.RetrofitInstance
import com.dev20.themarsroll.db.PhotosDatabase
import com.dev20.themarsroll.models.Photo

class MarsPhotoRepository(
    val db: PhotosDatabase
) {
    suspend fun getCuriosityPhotos(solQuery: Int, roverQuery: Int, camera: String) =
        RetrofitInstance.api.getCuriosityData(solQuery, roverQuery, camera)

    suspend fun upsert(photo: Photo) = db.getPhotoDao().upsert(photo)

    fun getSavedPhotos() = db.getPhotoDao().getAllPhotos()

    suspend fun deletePhoto(photo: Photo) = db.getPhotoDao().deletePhoto(photo)
}
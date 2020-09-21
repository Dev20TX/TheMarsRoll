package com.dev20.themarsroll.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dev20.themarsroll.models.Photo

@Dao
interface MarsPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun upsert(photo: Photo): Long

    @Query("SELECT * FROM photos")
    fun getAllPhotos(): LiveData<List<Photo>>

    @Delete
    suspend fun deletePhoto(photo: Photo)
}
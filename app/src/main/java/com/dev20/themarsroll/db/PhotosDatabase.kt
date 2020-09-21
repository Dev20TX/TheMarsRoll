package com.dev20.themarsroll.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev20.themarsroll.models.Photo


@Database(
    entities = [Photo::class],
    version = 1
)

abstract class PhotosDatabase : RoomDatabase() {

    abstract fun getPhotoDao(): MarsPhotoDao

    companion object {
        @Volatile
        private var instance: PhotosDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PhotosDatabase::class.java,
                "photos_db.db"
            ).build()
    }
}
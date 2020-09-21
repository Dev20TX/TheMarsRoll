package com.dev20.themarsroll.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "photos"
)

@TypeConverters
data class Photo(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("earth_date")
    val earthDate: String,
    @SerializedName("img_src")
    val imgSrc: String,
    val sol: Int,
    @SerializedName("rover_id")
    val rover: Int,
) : Serializable
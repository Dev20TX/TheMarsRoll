package com.dev20.themarsroll.api

import com.dev20.themarsroll.models.MarsPhotos
import com.dev20.themarsroll.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsPhotoApi {

    @GET("/mars-photos/api/v1/rovers/curiosity/photos")

    suspend fun getCuriosityData(
        @Query("sol")
        solQuery: Int,
        @Query("rover_id")
        roverQuery: Int,
        @Query("camera")
        camera: String,
        @Query("api_Key")
        apiKey: String = API_KEY
    ): Response<MarsPhotos>
}



package com.dev20.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev20.themarsroll.models.MarsPhotos
import com.dev20.themarsroll.models.Photo
import com.dev20.themarsroll.repository.MarsPhotoRepository
import com.dev20.themarsroll.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MarsPhotoViewModel(

    private val marsPhotoRepository: MarsPhotoRepository
    ): ViewModel() {

    val marsPhotos: MutableLiveData<Resource<MarsPhotos>> = MutableLiveData()

    init {
        getRandomPhotos()
    }

     fun getCuriosityPhotos(solQuery: Int, roverQuery: Int, camera: String) = viewModelScope.launch {
        marsPhotos.postValue(Resource.Loading())
        val response = marsPhotoRepository.getCuriosityPhotos(solQuery, roverQuery, camera)
        marsPhotos.postValue(handlePhotosResponse(response))
    }

    private fun handlePhotosResponse(response: Response<MarsPhotos> ) : Resource<MarsPhotos> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getRandomPhotos() {
        getCuriosityPhotos((1..2878).random(), 5, "NAVCAM")
    }

    fun savePhoto(photo: Photo) = viewModelScope.launch {
        marsPhotoRepository.upsert(photo)
    }

    fun getSavedPhotos() = marsPhotoRepository.getSavedPhotos()

    fun deletePhoto(photo: Photo) = viewModelScope.launch {
        marsPhotoRepository.deletePhoto(photo)
    }
}
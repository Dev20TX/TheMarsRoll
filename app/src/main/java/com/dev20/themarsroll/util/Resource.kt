package com.dev20.themarsroll.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    //will inherit from our resource class - pass data in constructor
    class Success<T>(data: T) : Resource<T>(data)
    //Will take a message in constructor. Error is not nullable because if we get an error then we have an error message
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    //When request is fired off, we will emit the loading state and when the response comes, we will instead emmit the success or error state
    class Loading<T>: Resource<T>()
}
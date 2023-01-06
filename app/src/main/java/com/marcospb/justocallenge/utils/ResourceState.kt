package com.marcospb.justocallenge.utils

sealed class ResourceState<out T> {
    data class Success<T>(val data: T?) : ResourceState<T>()
    data class Error<T>(val data: T? = null, val exception: Exception) :
        ResourceState<T>()

    data class Loading<T>(val data: T? = null) : ResourceState<T>()
}

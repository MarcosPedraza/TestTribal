package com.marcospb.testtribal.utils

sealed class ResourceState<out T> {

    data class SUCCESS<T>(val data: T) : ResourceState<T>()
    data class ERROR<T>(val data: T? = null, val error: Exception) : ResourceState<T>()

    data class LOADING<T>(val data: T?) : ResourceState<T>()

}

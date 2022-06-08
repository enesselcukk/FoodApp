package com.eneselcuk.foodapp.util


sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String? = null) : Resource<T>(data, message)
}

enum class FoodStatus {
    LOADING, ERROR, DONE
}
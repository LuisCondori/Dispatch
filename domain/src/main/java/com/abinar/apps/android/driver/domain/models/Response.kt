package com.abinar.apps.android.driver.domain.models

sealed class Response<out T> {
    class Loading<out T> : com.abinar.apps.android.driver.domain.models.Response<T>()
    data class Success<out T>(val data: T) : com.abinar.apps.android.driver.domain.models.Response<T>()
    data class Fail<out T>(val e: Exception) : com.abinar.apps.android.driver.domain.models.Response<T>()
}
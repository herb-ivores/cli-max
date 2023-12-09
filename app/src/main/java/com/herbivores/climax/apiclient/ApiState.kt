package com.herbivores.climax.apiclient

sealed class ApiState<T: Any> {
    class Loading<T: Any> : ApiState<T>()
    class Failure<T: Any>(val value: Throwable) : ApiState<T>()
    data class Success<T: Any>(val data: T) : ApiState<T>()
}

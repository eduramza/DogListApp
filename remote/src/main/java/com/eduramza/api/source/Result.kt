package com.eduramza.api.source

sealed class MyResult<T> {
    data class Progress<T>(var loading: Boolean): MyResult<T>()
    data class Success<T>(val data: T): MyResult<T>()
    data class Failure<T>(val error: Throwable): MyResult<T>()

    companion object{
        fun <T> loading(isLoading: Boolean): MyResult<T> = Progress(isLoading)
        fun <T> sucess(data: T): MyResult<T> = Success(data)
        fun <T> failure(e: Throwable): MyResult<T> = Failure(e)
    }
}
package com.example.moduledb.controlDB.utils

sealed class NetResult<out T> {

    object StarLoading : NetResult<Nothing>()

    object StopLoading : NetResult<Nothing>()

    data class Success<out T>(val data: T): NetResult<T>()

    data class SuccessTwoData<out T, out U>(val data: T, val extraData: U) : NetResult<T>()


    data class Error(val errorMessage: String, val errorCode : String? = null) : NetResult<Nothing>() {
        init {
            println("AppModule NET ERROR :$errorMessage")
        }
    }
}
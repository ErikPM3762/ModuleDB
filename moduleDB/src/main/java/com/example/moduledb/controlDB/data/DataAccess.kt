package com.example.moduledb.controlDB.data

import com.example.services.data.DataResult
import retrofit2.Response

suspend inline fun <I, O, U> performUpdateOperation(
    crossinline call: suspend () -> Response<I>,
    crossinline deserialize: (I?) -> O?,
    crossinline saveResult: suspend (O) -> U
): DataResult<U, Exception> {
    return try {
        val response = call()
        if (response.isSuccessful && response.body() != null) {
            val result = deserialize(response.body())
            if (result != null) {
                DataResult.Success(saveResult(result))
            } else {
                DataResult.Error(Exception())
            }
        } else {
            DataResult.Error(Exception())
        }
    } catch (e: Exception) {
        DataResult.Error(Exception())
    }
}
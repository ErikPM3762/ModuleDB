package com.example.moduledb.controlDB.data

/**
 * DataResult ofrece una representación más detallada de errores al permitir almacenar instancias de Exception dentro de la clase Error.
 * Simplifica la gestión de la carga mediante un único objeto Loading.
 * La estructura clara y concisa de DataResult mejora la legibilidad y mantenibilidad del código.
 */

sealed class DataResult<out T, out E> {
    data class Success<out T>(val data: T) : DataResult<T, Nothing>()
    data class Error<out E>(val error: Exception) : DataResult<Nothing, E>()
    object Loading : DataResult<Nothing, Nothing>()
}
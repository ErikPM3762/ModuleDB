package com.example.moduledb.controlDB.data.remote.response



data class AuthTokenAwsResponse (
    val message: String,
    val error: Boolean,
    val success: Boolean,
    val data: AuthData
)
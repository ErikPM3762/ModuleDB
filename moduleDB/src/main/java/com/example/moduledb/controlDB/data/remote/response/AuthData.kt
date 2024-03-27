package com.example.moduledb.controlDB.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("id_token") val idToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("token_type") val tokenType: String
)

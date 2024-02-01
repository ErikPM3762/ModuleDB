package com.example.moduledb.controlDB.data.remote.request

import com.example.moduledb.controlDB.utils.Constants
import com.google.gson.annotations.SerializedName

data class AuthTokenAwsRequest(
    @SerializedName("usuario")
    val user: String = Constants.userAws,
    @SerializedName("password")
    val password: String = Constants.passwordAws,
    @SerializedName("email")
    val email: String = Constants.emailAws
)


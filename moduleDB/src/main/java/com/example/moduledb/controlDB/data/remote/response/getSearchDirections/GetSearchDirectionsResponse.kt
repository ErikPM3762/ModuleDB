package com.example.moduledb.controlDB.data.remote.response.getSearchDirections

import com.google.gson.annotations.SerializedName

data class GetSearchDirectionsResponse(
    @SerializedName("items")
    val items: List<Item>
)
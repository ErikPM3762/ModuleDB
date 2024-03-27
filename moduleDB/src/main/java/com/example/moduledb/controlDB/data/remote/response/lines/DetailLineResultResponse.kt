package com.example.moduledb.controlDB.data.remote.response.lines

import com.example.moduledb.controlDB.data.local.entities.MDbLinesDetail

data class DetailLineResultResponse(
    val busLine: List<MDbLinesDetail>
)

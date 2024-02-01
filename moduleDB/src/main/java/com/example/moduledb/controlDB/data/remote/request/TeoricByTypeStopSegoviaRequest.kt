package com.example.moduledb.controlDB.data.remote.request

data class TeoricByTypeStopSegoviaRequest(
     val idFront : Int,
     val country : String,
     val state : String,
     val cityOrTown : String,
     val idLocalCompany : String,
     val idBusLine : String,
     val tripCode : String
)

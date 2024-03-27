package com.example.moduledb.controlDB.data.remote.request

data class TeoricByTypeStopRequest(
     val idFront : Int,
     val country : String,
     val state : String,
     val cityOrTown : String,
     val idLocalCompany : String,
     val idBusLine : String,
     val tripCode : String
)

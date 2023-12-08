package com.example.moduledb.controlDB.data.mapers

import com.example.moduledb.controlDB.data.entities.PointsInterest
import com.example.moduledb.controlDB.data.models.PointsInterestResponse


fun PointsInterestResponse.toPointsInterest(): PointsInterest = PointsInterest(
    id = this.id.toLongOrNull() ?: 0L,
    idPointOfInterest = this.id,
    pointOfInterest = this.name,
    descPointOfInterest = this.description,
    pointOfInterestAddress = this.address,
    pointOfInterestPhone = this.phone ?: "",
    latitude = this.latitude,
    longitude = this.longitude
)


fun List<PointsInterestResponse>.toPointsInterestList(): List<PointsInterest> =
    this.map { it.toPointsInterest() }
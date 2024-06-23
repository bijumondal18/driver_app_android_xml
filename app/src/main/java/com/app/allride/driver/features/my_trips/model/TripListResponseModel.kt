package com.app.allride.driver.features.my_trips.model

data class TripListResponseModel(
    val `data`: List<TripData>,
    val links: Links,
    val meta: Meta
)
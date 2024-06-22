package com.app.allride.driver.features.trip_details.model

import com.app.allride.driver.features.my_trips.model.TripData

data class TripDetailsResponseModel(
    val `data`: TripData,
    val message: String,
    val status: Int,
    val success: Boolean
)
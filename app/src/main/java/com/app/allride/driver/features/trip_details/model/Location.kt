package com.app.allride.driver.features.trip_details.model

data class Location(
    val address_components: List<AddressComponent>,
    val description: String,
    val formatted_address: String,
    val latitude: Double,
    val longitude: Double,
    val name: String
)
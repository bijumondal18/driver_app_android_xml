package com.app.allride.driver.features.my_trips.model

data class LocationX(
    val address_components: List<AddressComponent>,
    val description: String,
    val formatted_address: String,
    val latitude: Double,
    val longitude: Double,
    val name: String
)